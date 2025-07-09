package cn.xsword.sshmanage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.xsword.sshmanage.mapper")
public class SshManageApplication {

    public static void main(String[] args) {
        SpringApplication.run(SshManageApplication.class, args);
    }

}
