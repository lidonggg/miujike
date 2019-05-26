#依赖镜像名称和ID
FROM centos:7
#指定镜像创建者信息
MAINTAINER njc
#切换工作目录
WORKDIR /usr
RUN mkdir  /usr/local/java
#ADD 是相对路径jar,把java添加到容器中
ADD ../../usr/local/src/jdk-8u172-linux-x64.tar.gz /usr/local/java/
 
#配置java环境变量
ENV JAVA_HOME /usr/local/java/jdk1.8.0_172
ENV JRE_HOME $JAVA_HOME/jre
ENV CLASSPATH $JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar:$JRE_HOME/lib:$CLASSPATH
ENV PATH $JAVA_HOME/bin:$PATH



