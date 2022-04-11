package hello.core.beanfind;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextInfoTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("Print whole Bean")
    void findAllBean(){
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for(String beanDefinitionName : beanDefinitionNames){ // iter >> 이터레이터. 리스트에서 값을 하나씩 꺼내서 읽어줌.
            Object bean = ac.getBean(beanDefinitionName);
            System.out.println("name = " + beanDefinitionName + " object = " + bean);
        }

    }

    @Test
    @DisplayName("Print  ApplicationBean")
    void findApplicationBean(){
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for(String beanDefinitionName : beanDefinitionNames){ // iter >> 이터레이터. 리스트에서 값을 하나씩 꺼내서 읽어줌.
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName); // Bean하나하나에 대한 정보들

            // 스프링 내부 빈이 아닌, 내가 등록한 빈.
            // AppConfig에 내가 직접 등록한 빈만 출력됨.
            if(beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION){
                Object bean = ac.getBean(beanDefinitionName);
                System.out.println("name = " + beanDefinitionName + "\nobject = " + bean);
            }

        }

    }
}
