package com.google.ssmm.common;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.GeneratedXmlFile;
import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.codegen.AbstractJavaGenerator;
import org.mybatis.generator.codegen.mybatis3.IntrospectedTableMyBatis3Impl;
import org.mybatis.generator.config.PropertyRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * 想创建一个完全不同的代码生成器， 使用一个继承了org.mybatis.generator.api.IntrospectedTable类的权限定类名
 * @author Walter
 *
 */
public class IntrospectedTableEcoTechImpl extends IntrospectedTableMyBatis3Impl {

    private static final Logger logger = LoggerFactory.getLogger(IntrospectedTableEcoTechImpl.class);

    @Override
    public List<GeneratedXmlFile> getGeneratedXmlFiles() {
        List<GeneratedXmlFile> answer = new ArrayList<GeneratedXmlFile>();

        if (xmlMapperGenerator != null) {
            Document document = xmlMapperGenerator.getDocument();
            GeneratedXmlFile gxf = new GeneratedXmlFile(document,
                getMyBatis3XmlMapperFileName(), getMyBatis3XmlMapperPackage(),
                context.getSqlMapGeneratorConfiguration().getTargetProject(),
                false, context.getXmlFormatter());
            if (context.getPlugins().sqlMapGenerated(gxf, this)) {
                answer.add(gxf);
            }
        }

        return answer;
    }

    @Override
    public List<GeneratedJavaFile> getGeneratedJavaFiles() {
        List<GeneratedJavaFile> answer = new ArrayList<GeneratedJavaFile>();

        for (AbstractJavaGenerator javaGenerator : javaModelGenerators) {
            List<CompilationUnit> compilationUnits = javaGenerator
                .getCompilationUnits();
            for (CompilationUnit compilationUnit : compilationUnits) {
                GeneratedJavaFile gjf = new GeneratedJavaFile(compilationUnit,
                    context.getJavaModelGeneratorConfiguration()
                        .getTargetProject(),
                    context.getProperty(PropertyRegistry.CONTEXT_JAVA_FILE_ENCODING),
                    context.getJavaFormatter());
                answer.add(gjf);
            }
        }

        for (AbstractJavaGenerator javaGenerator : clientGenerators) {
            List<CompilationUnit> compilationUnits = javaGenerator
                .getCompilationUnits();
            for (CompilationUnit compilationUnit : compilationUnits) {
                GeneratedJavaFile gjf = new GeneratedJavaFile(compilationUnit,
                    context.getJavaClientGeneratorConfiguration()
                        .getTargetProject(),
                    context.getProperty(PropertyRegistry.CONTEXT_JAVA_FILE_ENCODING),
                    context.getJavaFormatter());
                if (!existsFile(gjf)) {
                    answer.add(gjf);
                }
            }
        }
        return answer;
    }


    private boolean existsFile(GeneratedJavaFile generatedJavaFile) {
        boolean isExists = false;
        String fileSeparator = System.getProperty("file.separator");
        StringBuilder fileName = new StringBuilder();
        try {
            fileName.append(generatedJavaFile.getTargetProject());
            fileName.append(fileSeparator);
            fileName.append(generatedJavaFile.getTargetPackage().replaceAll("\\.", "/"));
            fileName.append(fileSeparator);
            fileName.append(generatedJavaFile.getFileName());
            logger.info("Mapper.java=" + fileName.toString());
            File javaFile = new File(fileName.toString());
            if (javaFile.exists()) {
                return true;
            }
        } catch (Exception e) {
            logger.info("[WARNING] Existing file exception");
        }
        return isExists;
    }

}
