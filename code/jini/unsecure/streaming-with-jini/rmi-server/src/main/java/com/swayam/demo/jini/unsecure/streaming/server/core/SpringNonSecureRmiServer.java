package com.swayam.demo.jini.unsecure.streaming.server.core;

import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.util.StopWatch;

import com.sun.jini.start.ServiceStarter;
import com.swayam.demo.jini.unsecure.streaming.server.config.RmiServerConfig;
import com.swayam.demo.jini.unsecure.streaming.server.core.reggie.ReggieStarterConfiguration;

public class SpringNonSecureRmiServer {

    private static final Logger LOG = LoggerFactory.getLogger(SpringNonSecureRmiServer.class);

    public static void main(String[] args) throws InterruptedException {

	CountDownLatch signalToStartServerThread = new CountDownLatch(1);

	Thread reggieStarterThread = new Thread(new ReggieStarter(signalToStartServerThread));
	reggieStarterThread.setName(ReggieStarter.class.getSimpleName());
	reggieStarterThread.start();

	Thread rmiServerStarterThread = new Thread(new RMIServerStarter(signalToStartServerThread));
	rmiServerStarterThread.setName(RMIServerStarter.class.getSimpleName());
	rmiServerStarterThread.start();

    }

    private static class ReggieStarter implements Runnable {

	private final CountDownLatch signalToStartServerThread;

	public ReggieStarter(CountDownLatch signalToStartServerThread) {
	    this.signalToStartServerThread = signalToStartServerThread;
	}

	@Override
	public void run() {
	    StopWatch stopWatch = new StopWatch();
	    stopWatch.start();

	    String policyFilePath = SpringNonSecureRmiServer.class.getResource("/policy.all").getFile();

	    LOG.info("Starting with the policy file {}", policyFilePath);

	    System.setProperty("java.security.policy", policyFilePath);

	    ServiceStarter.main(new ReggieStarterConfiguration(SpringNonSecureRmiServer.class.getResource("/jeri-reggie.config")));

	    try {
		Thread.sleep(1_000);
	    } catch (InterruptedException e) {
		LOG.error("Reggie was interrupted while starting up", e);
		throw new RuntimeException(e);
	    }

	    stopWatch.stop();

	    LOG.info("***************** Started Reggie successfully, took {} ms to start", stopWatch.getLastTaskTimeMillis());

	    signalToStartServerThread.countDown();

	}

    }

    private static class RMIServerStarter implements Runnable {

	private final CountDownLatch signalToStartServerThread;

	public RMIServerStarter(CountDownLatch signalToStartServerThread) {
	    this.signalToStartServerThread = signalToStartServerThread;
	}

	@Override
	public void run() {

	    try {
		signalToStartServerThread.await();
	    } catch (InterruptedException e) {
		LOG.error("RMI Server was interrupted while waiting for Reggie to start", e);
		throw new RuntimeException(e);
	    }

	    StopWatch stopWatch = new StopWatch();
	    stopWatch.start();

	    @SuppressWarnings("unused")
	    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(RmiServerConfig.class);
	    stopWatch.stop();
	    LOG.info("*********************The RMIServer is ready, took {} millis to start", stopWatch.getLastTaskTimeMillis());
	}

    }

}
