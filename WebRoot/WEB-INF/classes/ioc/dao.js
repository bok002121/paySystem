var ioc = {
		excelConf :{
			type : "org.nutz.ioc.impl.PropertiesProxy",
            fields : {
                paths : ["custom/excel.properties"]
            }
		},
        conf : {
            type : "org.nutz.ioc.impl.PropertiesProxy",
            fields : {
                paths : ["custom/db.properties"]
            }
        },
        dataSource : {
            type : "com.alibaba.druid.pool.DruidDataSource",
            events : {
                create : "init",
                depose : 'close'
            },
            fields : {
                url : {java:"$conf.get('db.url')"},
                username : {java:"$conf.get('db.username')"},
                password : {java:"$conf.get('db.password')"},
                testWhileIdle : true,
                validationQuery : {java:"$conf.get('db.validationQuery')"},
                maxActive : {java:"$conf.get('db.maxActive')"},
                filters : "mergeStat",
                connectionProperties : "druid.stat.slowSqlMillis=2000"
            }
        },
        sqlSource:{
        	type:"org.nutz.dao.impl.FileSqlManager",
        	args:["sql"]
        },
        dao : {
            type : "org.nutz.dao.impl.NutDao",
            args : [{refer:"dataSource"},{refer:"sqlSource"}]
        }
};