/*
 *    Copyright 2009-2021 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.apache.ibatis.executor.keygen;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;

import java.sql.Statement;

/**
 * @author Clinton Begin
 */
public interface KeyGenerator {

  // 针对Sequence主键而言，在执行insert sql前必须指定一个主键值给要插入的记录
  // Oracle、DB2
  void processBefore(Executor executor, MappedStatement ms, Statement stmt, Object parameter);

  // 针对自增主键的表，在插入时不需要主键，而是在插入过程自动获取一个自增的主键
  // MySQL，Postgresql
  void processAfter(Executor executor, MappedStatement ms, Statement stmt, Object parameter);

}
