# sharding-test
spring-maven-mybatis-sharding 

###此套代码采用了两个版本实现.sharding-1.0.0, sharding-1.3.1 
##sharding-1.0.0 实现分库分表的
主要的配置文件有spring-database, spring-sharding  采用比较传统的配置bean方法, 主要步骤如下(spring-sharding.xml):
1. <!--自定义分库分表逻辑所在包  -->		
<context:component-scan base-package="com.study.dangdang.sharding.jdbc" />

2.  <!-- 配置好dataSourceRulue,即对数据源进行管理 -->
    <bean id="dataSourceRule" class="com.dangdang.ddframe.rdb.sharding.api.rule.DataSourceRule">
        <constructor-arg>
            <map>
                <entry key="sharding_0" value-ref="sharding_0"/>
                <entry key="sharding_1" value-ref="sharding_1"/>
            </map>
        </constructor-arg>
    </bean>

3. talbeRule 配置分库分表策略. eg: studentTableRule
4.  <!-- 构成分库分表的规则 传入数据源集合和每个表的分库分表的具体规则 -->
    <bean id="shardingRule" class="com.dangdang.ddframe.rdb.sharding.api.rule.ShardingRule">
        <constructor-arg index="0" ref="dataSourceRule"/>
        <constructor-arg index="1">
            <list>
                <ref bean="userTableRule"/>
                <ref bean="studentTableRule"/>
            </list>
        </constructor-arg>

5. <!-- 对datasource进行封装 -->
    <bean id="shardingDataSource" class="com.dangdang.ddframe.rdb.sharding.api.ShardingDataSource">
        <constructor-arg ref="shardingRule"/>
    </bean>
    
    
6.    <!-- 配置sqlSessionFactory -->
     <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
        <property name="dataSource" ref="shardingDataSource"/>
        <property name="mapperLocations">
			<list>
				<value>classpath*:mapper/student/*Dao.xml</value>
			</list>
		</property> 
    </bean>
7.  <!-- 配置dal 层-->
    <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
        <property name="basePackage" value="com.qccr.sharding.dal"/>
        <property name="sqlSessionFactoryBeanName" value="sqlSessionFactory"/>
    </bean>



