package com.howtodoinjava.example.hessianclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.remoting.caucho.HessianProxyFactoryBean;

import com.howtodoinjava.hessianserver.hessian.HelloWorld;



@SpringBootApplication
public class HessianClientApplication {

	@Bean
	public HessianProxyFactoryBean hessianInvoker() {
		HessianProxyFactoryBean invoker = new HessianProxyFactoryBean();
		invoker.setServiceUrl("http://localhost:8080/hellohessian");
		invoker.setServiceInterface(HelloWorld.class);
		return invoker;
	}
	
	public static void main(String[] args) throws InterruptedException {
		ConfigurableApplicationContext context =  SpringApplication.run(HessianClientApplication.class, args);
		System.out.println("========Client Side===============");
		HelloWorld helloWorld =   	context.getBean(HelloWorld.class);


		int numOfThreads = 50;
		final int numberOfMessage = 1000;
		long start = System.currentTimeMillis();
		Thread[] threads = new Thread[numOfThreads];
		for (int i = 0; i<numOfThreads; i++) {
			threads[i] = new Thread(() -> {
				for (int j=0; j< numberOfMessage; j++) {
					int index = (int) (Math.random() * 1000);
					String s = helloWorld.sayHelloWithHessian(String.valueOf(index));
					if (j % 100 == 0) System.out.println(s);
				}
			});
			threads[i].start();
		}
		for (int i = 0; i<numOfThreads; i++) threads[i].join();
		long end = System.currentTimeMillis();
		String msg = String.format("%s Messages has been processed @ %s sec, avarege %s req/s",(numberOfMessage* numOfThreads), (end-start)/1000.0,(long )(numberOfMessage * numOfThreads * 1000)/(end-start));
		System.out.println(msg);

		System.exit(0);
	}
}
