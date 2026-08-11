package com.webrayan.agent;

import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import com.openai.models.ChatModel;
import com.openai.models.responses.*;
import com.webrayan.agent.entity.GetProduct;
import com.webrayan.agent.entity.Product;
import com.webrayan.agent.repository.ProductRepository;
import com.webrayan.agent.tools.ProductTool;

import java.util.List;

public class ProductAgent {

    private static final ChatModel MODEL = ChatModel.GPT_5_2;

    private final OpenAIClient client;
    private final ProductTool productTool;

    public ProductAgent(String apiKey) {
        this.client = OpenAIOkHttpClient.builder().apiKey(apiKey).build();
        this.productTool = new ProductTool(new ProductRepository());
    }

    public String ask(String question) {
        ResponseCreateParams params =
                ResponseCreateParams.builder()
                        .model(MODEL)
                        .addTool(GetProduct.class)
                        .input(question)
                        .build();

        Response response = client.responses().create(params);

        for (ResponseOutputItem item : response.output()) {
            if (item.isFunctionCall()) {
                ResponseFunctionToolCall toolCall = item.asFunctionCall();
                GetProduct request = toolCall.arguments(GetProduct.class);

                Product product = productTool.getProduct(request.productName);
                if (product == null) {
                    return "محصول یافت نشد: " + request.productName;
                }

                ResponseInputItem toolOutput =
                        ResponseInputItem.ofFunctionCallOutput(
                                ResponseInputItem.FunctionCallOutput.builder()
                                        .callId(toolCall.callId())
                                        .outputAsJson(product)
                                        .build()
                        );

                ResponseCreateParams secondRequest =
                        ResponseCreateParams.builder()
                                .model(MODEL)
                                .previousResponseId(response.id())
                                .inputOfResponse(List.of(toolOutput))
                                .build();

                Response finalResponse = client.responses().create(secondRequest);
                return finalResponse.toString();
            }
        }

        return response.toString();
    }
}
