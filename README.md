# MyBatis 重点学习指南

> 依据《MyBatis讲义》（动力节点，共 17 章，约 7500 行，`document-md文件打开需要安装typora工具/` 目录）
> 制定时间：2026-10-13 ｜ 目标：**3~4 天学完 MyBatis**，随后回 Spring6 学第 18 章整合（SSM 合体）
>
> **核心策略：与 Spring6 相反，MyBatis 的 XML 映射文件在企业里至今仍是主流写法，必须熟练手写。**
> 该花时间的是：**动态 SQL、resultMap、#{} vs ${}、多表映射**（天天用 + 面试高频）。
> 该省时间的是：**手写 GodBatis、javassist、WEB 整合章**（原理向亮点，不是必修）。

---

## 〇、优先级总览

| 优先级 | 含义 | 章节清单 |
|--------|------|----------|
| 🔥🔥🔥 必须精通 | 动手写 + 能给面试官讲明白 | 2 入门 / 3 CRUD / 8 接口代理 / 9.1 #{}和${} / 10 参数处理 / 11 查询专题 / 12 动态SQL / 13 高级映射 |
| 🔥🔥 重点掌握 | 理解 + 会用 | 4 核心配置文件 / 9 其余小技巧 / 14 缓存 / 16 PageHelper / 17 注解开发 |
| 🔥 快速过 | 半小时内浏览，知道有这回事 | 1 概述 / 6.4+6.5 对象作用域 / 15 逆向工程 |
| ⏭ 选做亮点 | 时间富余再做，最多半天 | 5 手写GodBatis（**5.5 总结必读**）/ 7 javassist / 6 其余部分 |

**如果时间只剩 3 天的最短路径：**
2 → 3 → 4（概念）→ 8 → 9.1 → 10 → 11 → 12 → 13 → 14 → 16 → 17

---

## 一、分章重点明细

### 第 1 章 MyBatis概述 🔥（半小时）
- 1.2 三层架构、1.3 JDBC 不足：和 Spring6 第 1 章的思路呼应，快速过
- 记住一句话定位：**MyBatis 是半自动化 ORM 框架（SQL 自己写，映射它来做）**，这是后面和 MyBatis-Plus / JPA 对比面试题的基础

### 第 2 章 入门程序 🔥🔥🔥（半天，必动手）
- **2.3 开发步骤是全书骨架**：建表 → Maven 依赖 → pojo → 核心配置文件 mybatis-config.xml → XxxMapper.xml → 测试类，完整跑通
- 2.4 核心配置文件的"名字和路径"规则：看懂即可，别背
- 2.6 JUnit / 2.7 logback：跟着配，能跑就行（logback.xml 直接抄讲义的，SpringBoot 里有更好的）
- **2.8 SqlSessionUtil 工具类必封装**，后面所有章节都靠它拿 SqlSession
- ⚠️ 讲义 2.2 教的是下载 zip 手动导 jar —— **别学这个姿势**，直接用 Maven 引 mybatis + mysql-connector-java

### 第 3 章 CRUD 🔥🔥🔥（半天，必动手）
- insert / delete / update / select 四个标签完整敲一遍，重点感受 **SQL 写在 XML 里、参数和结果自动映射**
- 3.4 查询时如果属性名和列名不一致（如 car_num vs carNum）会查出 null —— 先知道有这个坑，**系统解法在第 11.6 章**
- 3.5 namespace 的含义必须理解：它绑定 mapper 接口全限定名，连着第 8 章

### 第 4 章 核心配置文件详解 🔥🔥（2~3 小时，理解不用背）
- 4.1 environment（多套环境 default 切换）、4.2 transactionManager（JDBC/MANAGED）、4.3 dataSource（**UNPOOLED/POOLED 区别**，连接池概念面试可能问）
- 4.4 properties 抽取 jdbc.properties：和 Spring6 4.8 是同一个思想，一分钟看完
- 4.5 mapper 四种引入方式
- ⚠️ 常见坑：**mybatis-config.xml 的标签有严格顺序**（properties → settings → … → environments → mappers），顺序写错直接报错，报错了回来查这
- 认知：整合 Spring / SpringBoot 后，数据源和事务全被框架接管，这份文件会大幅缩水 → 所以**理解每个标签干什么即可，不背**

### 第 5 章 手写GodBatis ⏭（选做亮点，最多半天）
- 和 Spring6 第 11 章手写 mini-Spring 同一定位：看穿框架本质 + 面试差异化亮点
- 时间不够时的替代方案：**只读 5.5"重要实现原理总结"**（十几行讲清 SqlSessionFactoryBuilder → SqlSessionFactory → SqlSession 流水线），必读

### 第 6 章 WEB中应用MyBatis（MVC架构）⏭/🔥（只看两小节，半小时）
- 整章是 Tomcat + Servlet + HTML 的前后端不分离老架构，**你的方向是前后端分离 + SpringBoot，整章跳过**
- **但 6.4"MyBatis 对象作用域及事务问题"必看**：SqlSessionFactory（全局一个）/ SqlSession（方法级，用完关）/ 事务边界在 web 里怎么控制 —— 面试问"SqlSession 的作用域"就答这里
- 6.5"当前程序存在的问题"看一遍：它引出第 8 章接口代理为什么存在

### 第 7 章 javassist生成类 ⏭（跳过）
- 纯原理铺垫，为接口代理服务。直接跳，去第 8 章记结论

### 第 8 章 接口代理机制 🔥🔥🔥（1 小时，概念必须吃透）
- **一句话结论必背：Mapper 接口不用写实现类，MyBatis 在内存中用 JDK 动态代理生成实现** —— 和你 Spring6 第 14 章手写的 Proxy.newProxyInstance 是同一个东西
- 面试题 *"Mapper 接口的原理 / 为什么不用实现类"* 出处，能连到动态代理就是加分答案
- 学会用法：`sqlSession.getMapper(XxxMapper.class)`

### 第 9 章 MyBatis小技巧 🔥🔥🔥/🔥🔥（半天）
- **9.1 #{} 和 ${} 是全书面试第一高频 🔥🔥🔥**：
  - `#{}` 预编译占位（?），防 SQL 注入，**默认用它**
  - `${}` 字符串拼接，有注入风险，**只用于无法参数化的场景**：拼接表名 / 排序列名 / 批量删除 in 拼接
  - 模糊查询 like 的写法看一遍（#{} 拼 % 或 CONCAT）
- 9.2 typeAliases（typeAlias / package 两种）、9.3 mappers 四种方式（**package 方式是常用姿势**）：会用即可
- 9.4 IDEA 配置文件模板：设一个，后面新建 mapper.xml 省事
- **9.5 插入后获取自增主键**（useGeneratedKeys + keyProperty）：回填 id 是实战常用操作，必会

### 第 10 章 参数处理 🔥🔥🔥（2~3 小时）
- 单个简单类型 / 实体类参数：自然会用
- Map 参数：看懂即可（老代码里常见，新代码被 @Param 取代）
- **多参数：必会 `@Param` 注解**（命名参数，SQL 里用 #{命名} 取值）—— 多条件查询天天用
- 10.6 @Param 源码分析：快速过

### 第 11 章 查询语句专题 🔥🔥🔥（半天）
- 返回 Car / List\<Car\> / Map / List\<Map\> / Map\<String,Map\>：过一遍，知道 Map 承接"没有实体类"的场景
- **11.6 resultMap 是本章灵魂 🔥🔥🔥**：
  - 面试题 *"实体类属性名和表字段名不一致怎么办"* 的三个标准答案：**resultMap 显式映射 / 驼峰自动映射（mapUnderscoreToCamelCase）/ SQL 起别名**
- 11.7 查总记录条数：一分钟的事

### 第 12 章 动态SQL 🔥🔥🔥（1 天）全书最重要，没有之一
- **每个标签都要独立手写敲一遍**，这是以后写业务代码每天用的东西：
  - **if**（test 表达式判断拼接）+ **where**（自动去掉多余的 AND/OR）→ 多条件查询标配
  - **set**（更新时去掉多余逗号）
  - choose / when / otherwise（多选一）
  - **foreach**（collection / item / separator）→ **批量删除 + 批量插入必会**
  - sql + include（抽取公共字段片段复用）
  - trim：知道 where/set 就是它的特例即可
- 自测标准：给一个"按任意组合条件查询 + 按 id 列表批量删"，能闭眼写出整个 mapper

### 第 13 章 高级映射及延迟加载 🔥🔥🔥（1 天）
- **多对一（如 学生→班级）三种方式**：级联属性映射（了解）/ **association（必会）** / 分步查询（理解）
- **一对多（如 班级→学生列表）**：**collection（必会）** / 分步查询
- **延迟加载**：分步查询 + fetchType="lazy"，理解思想即可（用的时候才发第二条 SQL）
- 面试连带点：association 管多对一，collection 管一对多，别记反

### 第 14 章 缓存 🔥🔥（2 小时）
- **一级缓存**：SqlSession 级，默认开启，同一 SqlSession 相同查询直接命中；增删改会清空
- **二级缓存**：namespace（mapper）级，需手动开启（\<cache/\>），实体类需可序列化
- 面试标准答案就上面两行 + 一句"整合 Spring 后每次请求通常新建 SqlSession，一级缓存作用有限"
- 14.3 集成 EhCache：快速过，知道"第三方缓存实现"这个概念即可

### 第 15 章 逆向工程 🔥（1 小时）
- 了解 MBG 能根据表生成 pojo + mapper + example 即可，跑一次生成流程
- 认知：**实际开发你将用 MyBatis-Plus（自带更强代码生成），这章是理解概念**

### 第 16 章 PageHelper 🔥🔥（1~2 小时）
- 16.1 limit 手写分页：看懂 limit 语法和起始页计算即可（你 SQL 基础已有）
- **16.2 PageHelper 三步必会**：引依赖 → mybatis-config.xml 配插件 → 代码 `PageHelper.startPage(pageNum, pageSize)` 后紧跟查询
- 以后项目里的分页就靠它（或 MP 的分页插件）

### 第 17 章 注解式开发 🔥🔥（1~2 小时）
- @Insert / @Delete / @Update / @Select：注解写在接口方法上，简单 SQL 一行搞定，必须会
- 定位认知：**简单 SQL 用注解爽，复杂/动态 SQL 回 XML** —— 两种共存，按场景选
- 讲义注解部分只到基础 CRUD，动态 SQL 注解写法不必额外深究

---

## 二、面试高频题自测清单

学完后逐条自测，答不上来回看对应章节：

| # | 面试题 | 出处 | 掌握标准 |
|---|--------|------|----------|
| 1 | #{} 和 ${} 的区别？什么时候必须用 ${}？ | 9.1 | 预编译 vs 拼接 + 注入风险 + 表名/排序列名场景 |
| 2 | Mapper 接口为什么不用写实现类？ | 8/5.5 | JDK 动态代理生成实现（连 Spring6 第 14 章） |
| 3 | 实体类属性名和表字段名不一致怎么办？ | 11.6 | resultMap / 驼峰自动映射 / SQL 别名，三答案 |
| 4 | resultType 和 resultMap 的区别？ | 11.6 | 自动映射按名匹配 vs 手动显式映射 |
| 5 | 动态 SQL 用过哪些标签？ | 12 | if/where/set/choose/foreach/sql+include + 各举一场景 |
| 6 | 一级缓存和二级缓存的区别？ | 14 | 作用域、默认开关、失效条件 |
| 7 | @Param 注解的作用？ | 10.5 | 多参数命名，SQL 用 #{名} 取值 |
| 8 | MyBatis 的执行流程？ | 2/5.5 | Builder→Factory→Session→getMapper→执行 SQL→映射返回 |
| 9 | 插入数据后怎么拿到自增主键？ | 9.5 | useGeneratedKeys + keyProperty 回填 |
| 10 | MyBatis 支持延迟加载吗？怎么实现？ | 13 | 分步查询 + lazy，用到才查 |

---

## 三、时间规划（4 天，紧凑可压到 3 天）

| 天 | 内容 | 产出检验 |
|----|------|----------|
| Day 1 | 1（快过）→ 2 → 3 → 4 | 入门流程独立跑通；SqlSessionUtil 封装好；CRUD 四个标签全敲过 |
| Day 2 | 8 → 9 → 10 → 11 | getMapper 用法顺滑；#{}${} 能讲明白；@Param 多参数 + resultMap 驼峰都敲过 |
| Day 3 | 12 → 13 | 多条件查询 + foreach 批量删增闭眼写；association + collection 各敲一遍 |
| Day 4 | 14 → 15（快）→ 16 → 17 | 缓存两句话能背；PageHelper 跑通一次；注解版 CRUD 敲一遍 |

> 每章代码在 IDEA 的 Maven module 里敲（如 `mybatis-ch02` …），数据库用本地 MySQL（root/123456），先把讲义用的表（car 等）建好。
> Day 4 结束的当晚或次日，直接回 Spring6 第 18 章整合，趁热打铁把 SSM 串起来。

---

## 四、学习检验标准（学完 MyBatis 你应该能做到）

- [ ] 不看讲义，从建表到查询完整写一个 Maven 版 MyBatis 入门 demo
- [ ] 手写：多条件动态查询（if + where）+ foreach 批量删除
- [ ] 手写：多对一 association + 一对多 collection 的 resultMap
- [ ] 手写：@Param 多参数方法 + useGeneratedKeys 主键回填
- [ ] 手写：PageHelper 分页三步
- [ ] 能讲：#{} vs ${}、接口代理原理、一二级缓存（各 1 分钟）

---

## 五、避坑提醒

1. **别手动导 jar**——讲义 2.2 的"下载 zip 导入 lib"是过时姿势，全程用 Maven
2. **mybatis-config.xml 标签顺序报错**是最常见的环境坑，报错了先查顺序（第 4 章）
3. **第 6 章别陷进去**——Tomcat + 前后端不分离的 MVC 与你的方向无关，只看 6.4/6.5
4. **手写框架/javassist 是亮点不是必修**——3~4 天的排期里放不下就只读 5.5 的原理总结，SpringBoot 项目经验比它们更值钱
5. 逆向工程生成的 Example 风格代码了解即可，**你的主力未来是 MyBatis-Plus**
6. 查出 null 先想"列名和属性名是否一致"，条件反射式排查
7. 学完立刻回 Spring6 第 18 章整合——SqlSessionFactoryBean 就是 Spring6 7.4 的 FactoryBean，MapperScannerConfigurer 扫的就是这里的 mapper 接口，**两边知识正好合龙**
