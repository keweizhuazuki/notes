#### Docker
##### 基础知识
- 基础架构
  - ![alt text](image.png)
- docker安装：
  - `https://docs.docker.com/engine/install/centos/`
- docker自启
  - sudo systemctl enable --now docker
- docker启动：
  - `sudo systemctl start docker`

##### 命令
- 下载镜像相关命令
  - docker search：搜索镜像
  - docker pull：下载镜像
  - docker images：查看本地镜像
  - docker rmi：删除本地镜像
- 容器相关命令
  - docker run：运行容器
    - -d：后台运行
    - --name xxx：指定容器名
    - -p：端口映射
  - docker ps：查看容器
    - -a：查看所有容器（包括已停止的）
  - docker stop：停止容器
  - docker start：启动容器
  - docker restart：重启容器
  - docker stats：查看容器资源
  - docker logs：查看容器日志
  - docker exec：进入容器
    - -it：交互式进入
  - docker rm：删除容器
- 保存镜像相关命令
  - docker commit：保存镜像
    -  -m：提交信息
    -  -a：作者
    -  -p：提交暂停容器
    -  -c：提交容器时执行命令
  - docker save：导出镜像
    -  -o：导出到文件
  - docker load：导入镜像
    - -i：导入文件
    - -q：静默导入
- 分享镜像相关命令
  - docker login：登录docker
  - docker tag：修改镜像标签
    - 用户名/镜像名:版本
  - docker push 用户名/镜像名:版本：推送镜像

###### Docker存储
- 目录挂载
  - docker run -v /宿主机目录:/容器目录
- 卷映射
  - docker run -v 卷名:/容器目录
  - 默认卷目录：/var/lib/docker/volumes/<volume_name>/_data
- docker volume ls：查看卷
- docker volume inspect：查看卷详细信息