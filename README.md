# 0 绪论
    本项目的设计初衷系本人于macOS生态下并未找到适合自己的ssh管理工具，此前一直使用终端进行ssh连接，每次连接都要输入hostname ip password，且连接时间过长会断开连接，故设计实现本项目。
    本项目的技术栈为html + css + js + xterm.js + springboot + mybatis-plus + redis + spring security + jsch，拟实现用户注册登录、用户保存及管理服务器ssh连接信息、用户根据保存的连接信息一键访问远程服务器并在命令行界面进行shell命令交互、用户在图形化界面进行简单的文件管理等。
    本项目考虑到服务器性能问题，不对外开放注册功能，文档末尾将酌情给出供访客体验的 username 及 password，感兴趣的朋友可以去体验。ps：体验完后记得删除连接信息，本人承诺不会冒犯诸位的隐私。
    如有斧正，敬请联系，谢谢。
    个人网站：myweb.xsword.me
# 1 功能实现
## 1.1 登陆注册
    
## 1.2 Spring Security
    关于spring security方面，请参考该博客：https://blog.csdn.net/m0_74065705/article/details/142468012
## 1.3 ....