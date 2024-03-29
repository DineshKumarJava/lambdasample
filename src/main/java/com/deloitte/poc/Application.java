package com.deloitte.poc;

import com.amazonaws.serverless.exceptions.ContainerInitializationException;
import com.amazonaws.serverless.proxy.model.AwsProxyRequest;
import com.amazonaws.serverless.proxy.model.AwsProxyResponse;
import com.amazonaws.serverless.proxy.spring.SpringBootLambdaContainerHandler;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@SpringBootApplication
public class Application extends SpringBootServletInitializer
        implements RequestStreamHandler
{
    private static SpringBootLambdaContainerHandler<AwsProxyRequest, AwsProxyResponse> handler;

    static
    {
        try
        {
            handler = SpringBootLambdaContainerHandler.getAwsProxyHandler(
                    Application.class);
        } catch (ContainerInitializationException e)
        {
            // if we fail here. We re-throw the exception to force another cold start
            e.printStackTrace();
            throw new RuntimeException(
                    "Could not initialize Spring Boot application", e);
        }
    }

    public static void main(String args[])
    {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void handleRequest(InputStream input, OutputStream output,
                              Context context) throws IOException
    {
        handler.proxyStream(input, output, context);
        // just in case it wasn't closed by the mapper
        output.close();
    }
}
