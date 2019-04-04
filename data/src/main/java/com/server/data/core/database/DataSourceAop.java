package com.server.data.core.database;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Order(-1)
@Component
public class DataSourceAop {
	private final String POINT_CUT = "execution(* com.bdcom.*.service.*.*(..)";
	@Pointcut(POINT_CUT)
    public void pointCut() {
    }
	@Before("@annotation(targetDataSource)")
	public void doBefore(JoinPoint joinPoint, TargetDataSource targetDataSource) {
		DatabaseType dataType = targetDataSource.dataBaseType();
		/*if(dataType == DatabaseType.xlt){
			DatabaseContextHolder.setDatabaseType(DatabaseType.xlt);
		}else if(dataType == DatabaseType.quartz){
			DatabaseContextHolder.setDatabaseType(DatabaseType.quartz);
		}else if(dataType == null){
			DatabaseContextHolder.setDatabaseType(DatabaseType.quartz);
		}*/
		DatabaseType currentDataType = DatabaseContextHolder.getDatabaseType();
		if(dataType != currentDataType){
			DatabaseContextHolder.setDatabaseType(dataType);
		}
	}
}
