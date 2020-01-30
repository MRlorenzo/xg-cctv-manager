package com.xg.cctv;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.*;

/**
 * @Description:
 * @Author wuxubiao
 * @Date 2019/4/10 15:24
 * @Version V-1.0
 */
public class MybatisPlusGenerator {

    // 项目根目录
    private static final String PROJECT_PATH = "D:\\docs\\works\\idea\\xg-cctv-manager";
    // 包名
    private static final String PROJECT_ROOT_PACKAGE = "com.xg.cctv";
    // ModuleName
    private static final String PROJECT_MODULE_NAME = "";
    // Controller
    private static final String PROJECT_CONTROLLER = "controller";
    // entity
    private static final String PROJECT_ENTITY = "mybatis.po";
    // mapper
    private static final String PROJECT_MAPPER = "mybatis.mapper";
    // service
    private static final String PROJECT_SERVICE = "service";
    // ServiceImpl
    private static final String PROJECT_SERVICE_IMPL = "service.impl";

    // DriverName
    private static final String DB_DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
    // url
    private static final String DB_URL = "jdbc:mysql://localhost:3306/test2?useSSL=false&useUnicode=true&characterEncoding=UTF-8&useTimezone=true&serverTimezone=GMT%2B8";
    // username
    private static final String DB_USERNAME = "root";
    // password
    private static final String DB_PASSWORD = "654321";

    // mapper.xml输出目录
    private static final String MAPPER_XML_OUT_PUT_DIR = PROJECT_PATH
                                                        + File.separator + "src"
                                                        + File.separator + "main"
                                                        + File.separator + "resources"
                                                        + File.separator + "mapper";
    // mapper.xml.vm
    private static final String TEMPLATE_MAPPER_XML = "/templates/mapper.xml.vm";
    // controller.java.vm
    private static final String TEMPLATE_CONTROLLER = "templates/controller.java.vm";
    // service.java.vm
    private static final String TEMPLATE_SERVICE = "templates/service.java.vm";
    // serviceImpl.java.vm
    private static final String TEMPLATE_SERVICE_IMPL = "templates/serviceImpl.java.vm";
    // entity.java.vm
    private static final String TEMPLATE_ENTITY = "templates/entity.java.vm";
    // mapper.java.vm
    private static final String TEMPLATE_MAPPER = "templates/mapper.java.vm";

    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    private static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入" + tip + "：");
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    public static void main(String[] args) {
        //代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = getConfig();
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = getDataSource();
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = getPackage();
        mpg.setPackageInfo(pc);

        // 注入自定义配置，可以在 VM 中使用 cfg.abc 【可无】  ${cfg.abc}
        InjectionConfig cfg = getInjection();
        mpg.setCfg(cfg);

        // 自定义模板配置，可以 copy 源码 mybatis-plus/src/main/resources/templates 下面内容修改，
        // 放置自己项目的 src/main/resources/templates 目录下, 默认名称一下可以不配置，也可以自定义模板名称
        TemplateConfig tc = getTemplate();
        mpg.setTemplate(tc);

        StrategyConfig strategy = getStrategy();
        mpg.setStrategy(strategy);

        // 执行生成
        mpg.execute();

    }

    private static InjectionConfig getInjection(){
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-mp");
                this.setMap(map);
            }
        };

        //自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(TEMPLATE_MAPPER_XML) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return MAPPER_XML_OUT_PUT_DIR
                        + File.separator + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });

        cfg.setFileOutConfigList(focList);
        return cfg;
    }

    private static PackageConfig getPackage(){
        //String moduleName = scanner("包名");
        PackageConfig pc = new PackageConfig();
        pc.setParent(PROJECT_ROOT_PACKAGE);//包名
        pc.setModuleName(PROJECT_MODULE_NAME);
        pc.setController(PROJECT_CONTROLLER);
        pc.setEntity(PROJECT_ENTITY);
        pc.setMapper(PROJECT_MAPPER);
        pc.setService(PROJECT_SERVICE);
        pc.setServiceImpl(PROJECT_SERVICE_IMPL);
        return pc;
    }

    private static DataSourceConfig getDataSource(){
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);
        dsc.setTypeConvert(new MySqlTypeConvert());
        //mysql的JDBC驱动使用了新的包名.原：com.mysql.jdbc.Driver
        dsc.setDriverName(DB_DRIVER_NAME);
        dsc.setUrl(DB_URL);
        dsc.setUsername(DB_USERNAME);//用户名
        dsc.setPassword(DB_PASSWORD);//密码
        return dsc;
    }

    private static TemplateConfig getTemplate(){
        TemplateConfig tc = new TemplateConfig();
        tc.setController(TEMPLATE_CONTROLLER);
        tc.setService(TEMPLATE_SERVICE);
        tc.setServiceImpl(TEMPLATE_SERVICE_IMPL);
        tc.setEntity(TEMPLATE_ENTITY);
        tc.setMapper(TEMPLATE_MAPPER);
        tc.setXml(null);
        return tc;
    }

    private static GlobalConfig getConfig(){
        GlobalConfig gc = new GlobalConfig();

        gc.setOutputDir(PROJECT_PATH + File.separator+ "src"+File.separator+"main"+File.separator+"java");
        //+ "cctv-" + moduleName + File.separator+ "src"+File.separator+"main"+File.separator+"java"
        gc.setFileOverride(true);
        // 不需要ActiveRecord特性的请改为false
        gc.setActiveRecord(true);
        // XML 二级缓存
        gc.setEnableCache(false);
        // XML ResultMap
        gc.setBaseResultMap(true);
        // XML columList
        gc.setBaseColumnList(true);
        //gc.setSwagger2(true);// 实体属性 Swagger2 注解
        //是否生成 kotlin 代码
        //gc.setKotlin(true);
        gc.setAuthor("lorenzo");

        // 自定义文件命名，注意 %s 会自动填充表实体属性！
        // gc.setMapperName("%sDao");
        // gc.setXmlName("%sDao");
        // gc.setServiceName("MP%sService");
        // gc.setServiceImplName("%sServiceDiy");
        // gc.setControllerName("%sAction");
        gc.setServiceName("%sService");
        gc.setDateType(DateType.ONLY_DATE);
        return gc;
    }

    private static StrategyConfig getStrategy(){
        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        // strategy.setCapitalMode(true);// 全局大写命名 ORACLE 注意
        // 此处可以修改为表前缀(模块名称)
        //strategy.setTablePrefix("zkl_", "zkl_applet_");
        // 表名生成策略(下划线转驼峰命名)
        strategy.setNaming(NamingStrategy.underline_to_camel);
        // 列名生成策略(下划线转驼峰命名)
        //strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        // 需要生成的表
        strategy.setInclude(scanner("表名，多个英文逗号分割").split(","));
        // strategy.setExclude(new String[]{"test"}); // 排除生成的表
        // 自定义实体父类
        //strategy.setSuperEntityClass("");
        // 自定义实体，公共字段
        // strategy.setSuperEntityColumns(new String[] { "test_id", "age" });
        // 自定义 mapper 父类
        // strategy.setSuperMapperClass("com.baomidou.demo.TestMapper");
        // 自定义 service 父类
        // strategy.setSuperServiceClass("com.baomidou.demo.TestService");
        // 自定义 service 实现类父类
        // strategy.setSuperServiceImplClass("com.baomidou.demo.TestServiceImpl");
        // 自定义 controller 父类
        //strategy.setSuperControllerClass("com.xg.cctv.common.BaseController");
        // 【实体】是否生成字段常量（默认 false）
        // public static final String ID = "test_id";
        //strategy.setEntityColumnConstant(true);
        // 【实体】是否为构建者模型（默认 false）
        // public User setName(String name) {this.name = name; return this;}
        //strategy.setEntityBuilderModel(true);
        // 是否启动Lombok配置
        //strategy.setEntityLombokModel(true);
        // 是否启动REST风格配置
        strategy.setRestControllerStyle(true);
        // Boolean类型字段是否移除is前缀处理
        strategy.setEntityBooleanColumnRemoveIsPrefix(true);

        //strategy.setControllerMappingHyphenStyle(true);
        return strategy;
    }

}
