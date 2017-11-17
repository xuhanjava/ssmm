package com.google.ssmm.LabmbdaTest;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.ssmm.entity.Student;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by xuhan
 * 2017/5/26
 * 下午4:40
 */
public class LambdaStreamTest {
    @Test
    public void test1(){
        //下面的是转换过程
        Stream<Integer> integerStream = Arrays.asList(1, 2, 3).stream().filter(a -> a > 11);
        //filter接受的是一个Predicate。是一个函数式接口
        //boolean test(T t); 这是唯一的方法
        //forEach接受一个Consumer接口，也是一个函数式接口。
        //void accept(T t);这是唯一的方法就是在这里调用
        //integerStream是空但不是null的话不会报错

        //流只能terminal一次，所以执行两次的话就会报错。
        //integerStream.forEach(a -> System.out.println(a));
        integerStream.collect(Collectors.toList());


        Stream<List<Integer>> inputStream = Stream.of(
                Arrays.asList(1),
                Arrays.asList(2, 3),
                Arrays.asList(4, 5, 6)
        );
        Stream<Integer> outputStream = inputStream.
                flatMap((childList) -> childList.stream());
        outputStream.forEach(a -> System.out.println(a));
    }

    /**
     * 2.0
       4.0
       6.0
       8.0
       10.0
       12.0
       sum is:36.0
     */
    @Test
    public void test2(){
        List<Integer> nums = Arrays.asList(1,1,null,2,3,4,null,5,6,7,8,9,10);
        System.out.println("sum is:"+nums.stream().filter(num -> num != null).
                distinct().mapToDouble(num -> num * 2).
                peek(System.out::println).skip(2).limit(4).sum());
    }

    @Test
    public void test3(){
        //map其实是一个function接口的参数
        // <R> Stream<R> flatMap(Function<? super T, ? extends Stream<? extends R>> mapper);
        // <R> Stream<R> map(Function<? super T, ? extends R> mapper);
        Stream<Integer> ints = Arrays.asList(Arrays.asList(1, 2), Arrays.asList(3, 1)).stream().flatMap(a -> a.stream());

        /*ints.peek(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                System.out.println(integer);
            }
        });*/

        //这儿有一点需要注意的是 如果光有 peek而没有reduce的话就不会打印peek里面的那个lambda表达式的。
        //因为只是在reduce的时候才会执行 真正的转换。源码体现

        /*public final Stream<P_OUT> peek(Consumer<? super P_OUT> action) {
            Objects.requireNonNull(action);
            return new StatelessOp<P_OUT, P_OUT>(this, StreamShape.REFERENCE,
                    0) {
                @Override
                Sink<P_OUT> opWrapSink(int flags, Sink<P_OUT> sink) {
                    return new Sink.ChainedReference<P_OUT, P_OUT>(sink) {
                        @Override
                        public void accept(P_OUT u) {
                            action.accept(u);
                            downstream.accept(u);
                        }
                    };
                }
            };
        }*/
        //上面这段源码只是返回一个Stream对象但是还没有真正的消费。只有在reduce的时候才是会消费里面的这个函数式接口
        ints.peek(a -> System.out.println("123")).forEach(System.out::println);
    }

    @Test
    public void testRecude(){
        /*List<Integer> collect = Stream.of(1, 2, 3).distinct().collect(Collectors.toList());
        collect.forEach(a-> System.out.println(a+1));*/

        List<Integer> ints = Lists.newArrayList(1,2,3);
        Integer reduce = ints.stream().reduce(10, (sum, i) -> sum + i);
        System.out.println(reduce);
    }

    @Test
    public void testAllLearn(){
        List<Integer> collects = ImmutableList.of(1, 2).stream().collect(Collectors.toList());

        collects.forEach(a-> System.out.println(a));

    }

    @Test
    public void testLambda(){
        List<String> transform = Lists.transform(Arrays.asList(1, 2, 3), new Function<Integer, String>() {
            @Override
            public String apply(Integer integer) {
                return "value:" + integer;
            }
        });

        transform.forEach(a-> System.out.println(a));
        Arrays.asList(1,2,3).stream().map(a->"value:"+a).forEach(a-> System.out.println(a));
    }

    public java.util.function.Function testLambda1(){
        return a->a;
    }

    /**
     * 如果想最后的结果是个集合的话，用Collectors.collect();方法来生成任意类型的集合
     * 记住用lambda表达式
     * Collect方法底层需要的是Collector接口 Collectors.toCollection()底层需要的是
     * Supplier接口。这个接口是个函数式接口无参，只有方法的返回值。
     * map方法需要接受一个Function接口。
     * 这里有个问题是map() mapToInt()之后的intStream中有一个collect方法的重载。所以还是使用map这个方法，要不然老是用新的。
     * 在这里我想把对这些流的操作都抽出来做为一个公共的部分减少冗余，但是没想到的是流只能reduce操作一次。
     * Set接口最多只能包含一个null元素。不能包含相同元素。部分实现不能包含null元素。
     * 数据分组注释的例子现在还是不知道什么原因出的问题。
     */
    @Test
    public void testCollects(){
        TreeSet<String> treeSet = Stream.of(12,1,2,3,1).map(t -> "" + t).collect(Collectors.toCollection(TreeSet::new));

        //System.out.println(treeSet);
        //Collectors.toCollection(supper);
        Collector<Integer, ?, Optional<Integer>> collector = Collectors.maxBy((Integer a, Integer b) -> {
            return a - b;
        });

        Optional<Integer> optional = treeSet.stream().map(str -> Integer.valueOf(str)).collect(collector);
        //     System.out.println(optional.get());
        Double collect = treeSet.stream().map(str -> Integer.valueOf(str)).collect(Collectors.averagingInt(a -> a));
        //System.out.println("12,1,2,3 avg:"+collect);

        //数据分块。把一个流分为两部分。
        Map<Boolean, TreeSet<Integer>> collect1 = treeSet.stream().map(str -> Integer.valueOf(str)).collect(Collectors.partitioningBy((a -> a > 101), Collectors.toCollection(TreeSet::new)));
        TreeSet<Integer> trueSet = collect1.get(true);
        TreeSet<Integer> falseSet = collect1.get(false);
        //System.out.println(trueSet+":"+falseSet);

        //数据分组。类似sql中的group by
       /* Function<Integer,String> function=a -> ""+a;
        Map<Integer, List<Integer>> collect2 = treeSet.stream().map(str -> Integer.valueOf(str)).
                collect(Collectors.groupingBy(function,Collectors.toSet()));
        System.out.println(collect2);*/
       // 数据分组
        Student apple1 = new Student(1, "apple");
        Student apple2 = new Student(2,"apple");
        Student orange = new Student(3, "orange");
        Map<String, List<Student>> collect2 = Stream.of(apple1, apple2, orange).collect(Collectors.groupingBy(a -> a.getName()));
//        System.out.println(collect2);
        //mapping 允许在收集器的容器上执行类似 map 的操作。但是需要指明使用什么样的集合类 存储结果，比如 toList。这些收集器就像乌龟叠罗汉，龟龟相驮以至无穷
        Map<String, List<Integer>> collect4 = Stream.of(apple1, apple2, orange).collect(
                Collectors.groupingBy(a -> a.getName(), Collectors.mapping(a -> a.getStudentId(), Collectors.toList())));
        System.out.println(collect4);
        //统计数量的话还可以更简单
        Map<String, Long> collect3 = Stream.of(apple1, apple2, orange).collect(
                Collectors.groupingBy(a -> a.getName(), Collectors.counting()));


       // System.out.println(collect3);
        //字符串的拼接(之前用的是StringBuilder 还得判断头部元素，尾部元素)。第一个参数是分割符，第二个是前缀，第三个是后缀
        String joinStr = Stream.of("apple", "orange", "lizi").collect(Collectors.joining(",", "[", "]"));
      //  System.out.println(joinStr);

    }

    @Test
    public void testComparator(){
        //Comparable
        Comparator<Integer> c = (a,b)->a-b;
    }

    @Test
    public void testLambda123(){
        //像这样的filter 每一行都打印出来
        /*long count = Stream.of(1, 3, 4).filter(a -> {
            System.out.println(a);
            return a > 3;
        }).count();*/
        //System.out.println("count="+count);

        //flatMap的作用 <R> Stream<R> flatMap(Function<? super T, ? extends Stream<? extends R>> mapper); 这就是很明显了
        List<Integer> together = Stream.of(Arrays.asList(1, 2), Arrays.asList(3, 4))
                .flatMap(numbers -> numbers.stream())
                .collect(Collectors.toList());

        //和之前的比较大小的简化，之前需要的是两个变量，现在不需要这么多了.需要注意里面的比较器的实现。是a-b还是b-a;
        //max返回一个Optional对象。这个对象可能存在，也可能不存在。若Stream是null那就不存在
        /*Integer integer = Stream.of(3, 11, 3, 1).max((a, b) -> {
            return a - b;
        }).get();*/
        // System.out.println("max="+integer);

        //reduce适合从一组值中生成一个值,求和.10是初始值 10-1-2-3
        Integer as = Stream.of(1, 2, 3).reduce(10, (a, b) ->
                a - b
        );
        System.out.println("reduce="+as);
    }

    /**
     * 要排序的对象必须继承comparable接口，否则报错
     */
    @Test
    public void testFunction(){
//        Set<Integer> numbers = new HashSet<>(Arrays.asList(12,3,4, 2, 1));
//        List<Integer> sameOrder = numbers.stream()
//                .sorted(Comparator)
//                .collect(Collectors.toList());
//        System.out.println(sameOrder);
        //如果集合是有序的，那么得到的流也是有序的。
        //如果集合是无序的，那么得到的流也是无序的。
        //也可以使用特殊的api把无序的集合变成有序的流。如上面的例子。如果流中的元素不会继承 Comparable的话，可能会报错。
        //如使用并行流时，forEach 方法不能保证元素是 按顺序处理的,如果需要保证按顺序处理，应该使用 forEachOrdered 方法
      /*  Comparator.comparing();
        Collectors.maxBy()*/

    }

    public Integer testAFun(Function<String,Double> t){
        return 1;
    }
    
    @Test
    public void testMapToInt(){
        Map<Integer, List<Integer>> collect = Stream.of(1, 3, 3, 5).collect(Collectors.groupingBy(i -> i));
        System.out.println(collect);
    }
}
