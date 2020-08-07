package com.zhkuchen.framework.mybatisplus.config;
//package com.zhkuchen.framework.mybatisplus.config;
//
//import javax.sql.DataSource;
//
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.mapstruct.Qualifier;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
//
//import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
//
//@Configuration
//public class MybatisPlusFactory {
//	 @Bean("sqlSessionFactory")
//	    @Primary
//	    public SqlSessionFactory sqlSessionFactory(@Autowired   DataSource dataSource) throws Exception {
//
//	        MybatisSqlSessionFactoryBean sqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
//	        sqlSessionFactoryBean.setDataSource(dataSource);
//	        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml"));
//	        
////	        Interceptor interceptor = new PageInterceptor();
////	        Properties properties = new Properties();
////	        properties.setProperty("helperDialect", "sqlserver");
////	        properties.setProperty("offsetAsPageNum", "true");
////	        properties.setProperty("rowBoundsWithCount", "true");
////	        properties.setProperty("reasonable", "false");
////	        interceptor.setProperties(properties);
////	        sqlSessionFactoryBean.setPlugins(new Interceptor[] {interceptor});
//	        
//	        
//	        return sqlSessionFactoryBean.getObject();
//	        
//	    }
//}
