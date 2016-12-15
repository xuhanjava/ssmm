package com.google.ssmm;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.ssmm.elasticsearch.EsClient;
import com.google.ssmm.entity.Sort1;
import com.google.ssmm.entity.Student;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.get.MultiGetItemResponse;
import org.elasticsearch.action.get.MultiGetResponse;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.ClearScrollRequestBuilder;
import org.elasticsearch.action.search.ClearScrollResponse;
import org.elasticsearch.action.search.MultiSearchResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.script.Script;
import org.elasticsearch.script.ScriptService;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.sort.*;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by xuhan on 16-11-26.
 */
public class EsTest {
    private TransportClient esClient = null;
    private ObjectMapper mapper;

    @Before
    public void init() {
        esClient = EsClient.getInstance();
        mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Test
    public void test() {
        System.out.println(123);
    }

    @Test
    public void testCreateWithJson() throws JsonProcessingException {
        Student student = new Student(20114576, "Walter");
        byte[] bytes = mapper.writeValueAsBytes(student);
        IndexResponse response = esClient.prepareIndex("xuhan_index", "place", "3").setSource(bytes).get();
        // Index name
        String _index = response.getIndex();
        // Type name
        String _type = response.getType();
        // Document ID (generated or not)
        String _id = response.getId();
        // Version (if it's the first time you index this document, you will get: 1)
        long _version = response.getVersion();
        // isCreated() is true if the document is a new one, false if it has been updated
        DocWriteResponse.Result result = response.getResult();
    }

    @Test
    public void testGetById() {
        GetResponse response = esClient.prepareGet("xuhan_index", "place", "1").get();
    }

    @Test
    public void testDelete() {
        esClient.admin().indices().prepareDelete("student")
                .execute().actionGet();
        //esClient.prepareDelete("student", "place", "2").get();
    }

    /**
     * 会修改document的值。version+1.完全可以被上面的testCreateWithJson所替代.还是不一样。如果这个id type index不存在的话不会创建
     */
    @Test
    public void testUpdate() {
        UpdateResponse updateResponse = esClient.prepareUpdate("xuhan_index", "place", "3")
                .setScript(new Script("ctx._source.studentId = \"3\"", ScriptService.ScriptType.INLINE, null, null))
                .get();
    }

    @Test
    public void testMutilGet() {
        MultiGetResponse multiGetItemResponses = esClient.prepareMultiGet()
                .add("xuhan_index", "place", "1")
                .add("xuhan_index", "place", "2", "3", "4")
                .add("xuhan_index2", "place", "foo")  //其他索引 id
                .get();

        for (MultiGetItemResponse itemResponse : multiGetItemResponses) {
            GetResponse response = itemResponse.getResponse();
            if (response != null && response.isExists()) {
                String json = response.getSourceAsString();
                System.out.println(json);
            }
        }
    }

    /**
     * The bulk API allows one to index and delete several documents in a single request.
     */
    @Test
    public void testMget() throws JsonProcessingException {
        BulkRequestBuilder request = esClient.prepareBulk();
        Student student = new Student(1, "Walter");
        byte[] bytes = mapper.writeValueAsBytes(student);
        Student student2 = new Student(2, "Walter");
        byte[] bytes2 = mapper.writeValueAsBytes(student2);
        IndexRequestBuilder req1 = esClient.prepareIndex("student", "place", "1").setSource(bytes);
        IndexRequestBuilder req2 = esClient.prepareIndex("student", "place", "2").setSource(bytes2);
        request.add(req1);
        request.add(req2);

        BulkResponse bulkResponse = request.get();
        if (bulkResponse.hasFailures()) {
            // process failures by iterating through each bulk response item
            System.out.println("oh,error!");
        }
    }

    /**
     * 没有测试 bulkprocessor
     */
    @Test
    public void testBulkListening() {
    }

    /**
     * routing.默认的是以id作为routing的值。为什么使用 routing。。一个个性化的routing值可以确保相关的文档存储到同样的分片上
     * 不使用：请告诉我，USER1的文档数量一共有多少
     * 使用了:请告诉我，USER1的文档数量一共有多少，它就在第三个分片上，其它的分片就不要去扫描了”
     * index twitter:
     * curl -XPOST 'http://localhost:9200/twitter/tweet?routing=kimchy' -d '{
     * "  user" : "kimchy",
     * "  postDate" : "2009-11-15T14:12:12",
     * "  message" : "trying out Elasticsearch"
     * }
     * <p>
     * search:we can specify it as the routing, resulting in the search hitting only the relevant shard:
     * curl -XGET 'http://localhost:9200/twitter/tweet/_search?routing=kimchy' -d '{
     * "query": {
     * "bool" : {
     * "must" : {
     * "query_string" : {
     * "query" : "some query string here"
     * }
     * },
     * "filter" : {
     * "term" : { "user" : "kimchy" }
     * }
     * }
     * }
     * <p>
     * 注意：The routing parameter can be multi valued represented as a comma separated string.
     * This will result in hitting the relevant shards where the routing values match to.
     * <p>
     * SearchType:
     * es：因为搜寻的数据天生就要排序，而数据又不是在一个分片上。客户端向这几个分片发出请求（客户端要查询的数据是5个，按日期排序）
     * 那这几个分片就都会按日期排序查出5个数据。返回给客户端，然后客户端再进行处理
     * query then fetch （默认的搜索方式):查询分片两次。第一次查询分片把分片排名和排序有关的信息返回给客户端，注意，不包括文档document)，
     * 然后按照各分片返回的分数进行重新排序和排名，取前size个文档。然后进行第二步，去相关的shard取document。这种方式返回的document与用户要求的size是相等的。
     * <p>
     * query and fetch
     * 向索引的所有分片（shard）都发出查询请求，各分片返回的时候把元素文档（document）和计算后的排名信息一起返回。这种搜索方式是最快的。因为相比下面的几种搜索方式，
     * 这种查询方法只需要去shard查询一次。但是各个shard返回的结果的数量之和可能是用户要求的size的n倍。
     * <p>
     * 显然如果使用DFS_QUERY_THEN_FETCH这种查询方式，效率是最低的，因为一个搜索，可能要请求3次分片。但，使用DFS方法，搜索精度应该是最高的。
     */

    @Test
    public void testSearch() {
        SearchResponse response = esClient.prepareSearch("xuhan_index") //,"index2"
                .setTypes("place")  //, "type2"
                //.setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setQuery(QueryBuilders.termQuery("name", "Walter"))                 // Query
                //.setPostFilter(QueryBuilders.rangeQuery("studentId").from(1).to(5))     // Filter
                // .setFrom(0).setSize(60).setExplain(true)
                // .setSize(100000)  //Result window is too large, from + size must be less than or equal to: [10000] but was [100000]. See the scroll api for a more efficient way to request large data sets.
                .execute()
                .actionGet();
        System.out.println(123);
    }

    /**
     * 好像没什么卵用
     */
    @Test
    public void tsetSearchWithMapping() {
        // InputStream resourceAsStream = getClass().getResourceAsStream("/classes/com/google/ssmm/elasticsearch/"
        //    + "");
        createWithMapping("/product.json", "xuhan_index");
    }

    private void createWithMapping(String url, String index) {
        InputStream resourceAsStream = getClass().getResourceAsStream(url);

        StringBuilder sb = new StringBuilder();

        BufferedReader reader = null;
        String json = null;
        try {
            reader = new BufferedReader(new InputStreamReader(resourceAsStream, "utf-8"));
            String line = reader.readLine();
            while (line != null) {
                sb.append(line);
                line = reader.readLine();
            }
            json = sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        esClient.admin().indices().prepareCreate(index);
    }

    /**
     * scroll相当于数据库里面的cursor。当我search request返回一个 page of results,the scroll 返回大量数据甚至全部数据
     * 不是用来处理实时的请求的，而是用来处理大量数据的。
     * e.g. in order to reindex the contents of one index into a new index with a different configuration.
     * 一般搜索请求都是返回一"页"数据，无论数据量多大都一起返回给用户，Scroll API可以允许我们检索大量数据（甚至全部数据）。
     * Scroll API允许我们做一个初始阶段搜索并且持续批量从Elasticsearch里拉取结果直到没有结果剩下。这有点像传统数据库里的cursors（游标）。
     */
    @Test
    public void testScroll() {
        QueryBuilder qb = QueryBuilders.termQuery("name.keyword", "Walter");

        SearchResponse scrollResp = esClient.prepareSearch("xuhan_index")
                .addSort(FieldSortBuilder.DOC_FIELD_NAME, SortOrder.ASC)  //当scroll多的时候这个最好设计成这个最好
                .setScroll(new TimeValue(60000))   //毫秒。只有在这个时间内才能使用这个scrollId
                .setQuery(qb)
                .setSize(1).execute().actionGet(); //max of 100 hits will be returned for each scroll
        //Scroll until no hits are returned
        do {
            for (SearchHit hit : scrollResp.getHits().getHits()) {
                //Handle the hit...
                System.out.println(hit.getSource().toString());
            }

            scrollResp =
                    esClient.prepareSearchScroll(scrollResp.getScrollId()).setScroll(new TimeValue(60000)).execute()
                            .actionGet();
        } while (scrollResp.getHits().getHits().length
                != 0); // Zero hits mark the end of the scroll and the while loop.
    }

    /**
     * 虽然当滚动有效时间已过，搜索上下文(Search Context)会自动被清除，但是一值保持滚动代价也是很大的，所以当我们不在使用滚动时要尽快使用Clear-Scroll API进行清除。
     */
    @Test
    public void testClearScrollId() {
        List<String> scrollIdList = new ArrayList<>();
        ClearScrollRequestBuilder clearScrollRequestBuilder = esClient.prepareClearScroll();
        clearScrollRequestBuilder.setScrollIds(scrollIdList); // addScrollId() 添加单个id
        ClearScrollResponse response = clearScrollRequestBuilder.get();
        System.out.println(response.isSucceeded());
    }


    @Test
    public void mutilSearch() {
        SearchRequestBuilder srb1 = esClient
                .prepareSearch("xuhan_index").setTypes("place").setQuery(QueryBuilders.queryStringQuery("Walter")).setSize(1);
        SearchRequestBuilder srb2 = esClient
                .prepareSearch("xuhan_index").setTypes("place").setQuery(QueryBuilders.matchQuery("name", "Walter")).setSize(1);

        MultiSearchResponse sr = esClient.prepareMultiSearch()
                .add(srb1)
                .add(srb2)
                .execute().actionGet();

        // You will get all individual responses from MultiSearchResponse#getResponses()
        long nbHits = 0;
        for (MultiSearchResponse.Item item : sr.getResponses()) {
            SearchResponse response = item.getResponse();
            nbHits += response.getHits().getTotalHits();
        }
    }

    /**
     * _doc has no real use-case besides being the most efficient sort order. So if you don’t care about the order in which documents are returned,
     * then you should sort by _doc. This especially helps when scrolling.
     * order:asc,desc
     * mode:avg……
     * field1:"stringValue",
     * field2:2,
     * field3:[3,10]
     * <p>
     * nested_path
     * Defines on which nested object to sort.
     * nested_filter
     * A filter that the inner objects inside the nested path should match
     * missing:当缺失这个值的时候该放在哪个位置
     * GET /_search
     * {
     * "sort" : [
     * { "price" : {"missing" : "_last"} } //_first 或者一个自定义的值
     * ],
     * "query" : {
     * "term" : { "product" : "chocolate" }
     * }
     * }
     */
    @Test
    public void testSort() throws JsonProcessingException {
        if (esClient.admin().indices().prepareExists("sort1").execute().actionGet().isExists() == false) {
            createWithMapping("/sort1.json", "sort1");
            BulkRequestBuilder request = esClient.prepareBulk();
            Sort1 sort1 = new Sort1(new Date(), "xuhan", "hanhan", 18);
            Sort1 sort2 = new Sort1(new Date(), "Walter", "tete", 28);
            byte[] bytes = mapper.writeValueAsBytes(sort1);
            byte[] bytes2 = mapper.writeValueAsBytes(sort2);
            IndexRequestBuilder req1 = esClient.prepareIndex("sort1", "sort1", "1").setSource(bytes);
            IndexRequestBuilder req2 = esClient.prepareIndex("sort1", "sort1", "2").setSource(bytes2);
            request.add(req1);
            request.add(req2);

            BulkResponse bulkResponse = request.get();
            if (bulkResponse.hasFailures()) {
                // process failures by iterating through each bulk response item
                System.out.println("oh,error!");
            }
        }
        SortBuilder sort1 = SortBuilders.fieldSort("age").sortMode(SortMode.MAX).order(SortOrder.ASC);
        SortBuilder sort2 = SortBuilders.fieldSort("post_date").sortMode(SortMode.MAX).order(SortOrder.ASC);
        //先按第一个排列，再按第二个排列
        SearchResponse searchResponse = esClient.prepareSearch("xuhan_index").setTypes("place").addSort(sort1).addSort(sort2).execute().actionGet();
       // searchResponse.getHits().hits();
    }

    /**
     * Metrics, Metrics 是简单的对过滤出来的数据集进行avg,max等操作，是一个单一的数值。
     * Bucket, Bucket 你则可以理解为将过滤出来的数据集按条件分成多个小数据集，然后Metrics会分别作用在这些小数据集上。
     * 对于最后聚合出来的结果，其实我们还希望能进一步做处理，所以有了Pipline Aggregations,其实就是组合一堆的Aggregations
     * 对已经聚合出来的结果再做处理。嵌套聚合能力
     */
    @Test
    public void testAggregations() {

    }
}
