package com.google.ssmm.common;

import java.util.List;

import com.google.ssmm.utils.TypeUtil;
import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 *
 * @author AJ.H
 *
 */
public class EcoTechExtandPlugin extends PluginAdapter {

    private static final Logger logger = LoggerFactory.getLogger(EcoTechExtandPlugin.class);

    @Override
    public boolean clientGenerated(Interface interfaze, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        clearMetheds(interfaze, topLevelClass, introspectedTable);
        return super.clientGenerated(interfaze, topLevelClass, introspectedTable);
    }

    @Override
    public boolean sqlMapDocumentGenerated(Document document, IntrospectedTable introspectedTable) {
        XmlElement parentElement = document.getRootElement();
        XmlElement selectCountByMapElement = new XmlElement("select");
        selectCountByMapElement.addAttribute(new Attribute("id", "getLastInsertedKey"));
        selectCountByMapElement.addAttribute(new Attribute("resultType", TypeUtil.LONG_TYPE));
        selectCountByMapElement.addElement(new TextElement("select last_insert_id()"));
        parentElement.addElement(selectCountByMapElement);
        return super.sqlMapDocumentGenerated(document, introspectedTable);
    }

    private void addSuperInterface(Interface interfaze, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        FullyQualifiedJavaType classType = new FullyQualifiedJavaType(TypeUtil.BASE_MAPPER_TYPE);
        classType.addTypeArgument(new FullyQualifiedJavaType(introspectedTable.getBaseRecordType()));
        interfaze.addSuperInterface(classType);
    }

    private void addImportClass(Interface interfaze, TopLevelClass topLevelClass) {
        FullyQualifiedJavaType classType = new FullyQualifiedJavaType(TypeUtil.BASE_MAPPER_TYPE);
        interfaze.addImportedType(classType);
    }

    private void clearMetheds(Interface interfaze, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        interfaze.getMethods().clear();
        addSuperInterface(interfaze, topLevelClass, introspectedTable);
        addImportClass(interfaze, topLevelClass);
    }

    @Override
    public boolean modelExampleClassGenerated(TopLevelClass topLevelClass,
        IntrospectedTable introspectedTable) {
        addSuperClass(topLevelClass);
        addImportClass(topLevelClass);
        return super.modelExampleClassGenerated(topLevelClass, introspectedTable);
    }

    @Override
    public boolean sqlMapSelectByExampleWithoutBLOBsElementGenerated(
        XmlElement element, IntrospectedTable introspectedTable) {
        XmlElement page = new XmlElement("if");
        page.addAttribute(new Attribute("test", "offset != null and limit != null"));
        page.addElement(new TextElement("limit ${offset} , ${limit}"));
        element.addElement(page);
        return super.sqlMapUpdateByExampleWithoutBLOBsElementGenerated(element,
            introspectedTable);
    }

    private void addSuperClass(TopLevelClass topLevelClass) {
        FullyQualifiedJavaType classType = new FullyQualifiedJavaType(TypeUtil.BASE_EXAMPLE_TYPE);
        topLevelClass.setSuperClass(classType);
    }

    private void addImportClass(TopLevelClass topLevelClass) {
        FullyQualifiedJavaType classType = new FullyQualifiedJavaType(TypeUtil.BASE_EXAMPLE_TYPE);
        topLevelClass.addImportedType(classType);
    }

    /**
     * @param topLevelClass
     * @param introspectedTable
     * @param fieldName
     */

    protected void addProperty(TopLevelClass topLevelClass, IntrospectedTable introspectedTable,
        String fieldName) {
        topLevelClass.addImportedType(new FullyQualifiedJavaType(TypeUtil.BASE_EXAMPLE_TYPE));
        CommentGenerator commentGenerator = context.getCommentGenerator();
        Field field = new Field();
        field.setVisibility(JavaVisibility.PROTECTED);
        field.setType(new FullyQualifiedJavaType(TypeUtil.INTEGER_TYPE));
        field.setName(fieldName);
        commentGenerator.addFieldComment(field, introspectedTable);
        topLevelClass.addField(field);

        char c = fieldName.charAt(0);
        String camel = Character.toUpperCase(c) + fieldName.substring(1);
        Method method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setName("set" + camel);
        method.addParameter(new Parameter(new FullyQualifiedJavaType(
            TypeUtil.INTEGER_TYPE), fieldName));
        method.addBodyLine("this." + fieldName + "=" + fieldName + ";");
        commentGenerator.addGeneralMethodComment(method, introspectedTable);
        topLevelClass.addMethod(method);

        method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);
        /*
         * method.setReturnType(new FullyQualifiedJavaType( baseExample));
         */
        method.setReturnType(new FullyQualifiedJavaType("java.lang.Integer"));
        method.setName("get" + camel);
        method.addBodyLine("return " + fieldName + ";");
        commentGenerator.addGeneralMethodComment(method, introspectedTable);
        topLevelClass.addMethod(method);
    }

    /**
     * This plugin is always valid - no properties are required
     */
    public boolean validate(List<String> warnings) {
        return true;
    }
}

