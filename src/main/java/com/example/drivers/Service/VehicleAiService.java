package com.example.drivers.Service;


import com.example.drivers.Model.Vehicle;
import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import com.openai.models.responses.Response;
import com.openai.models.responses.ResponseCreateParams;
import org.springframework.stereotype.Service;

@Service
public class VehicleAiService {

    private final OpenAIClient client = OpenAIOkHttpClient.fromEnv();

    public String describeVehicle(Vehicle vehicle) {
        String prompt = """
                اكتب وصفًا عربيًا قصيرًا من جملة أو جملتين لهذه السيارة
                لمساعدة المستخدم في مقارنة عروض السائقين.
                استخدم المعلومات المذكورة فقط.
                لا تفترض حالة السيارة أو سلامتها أو وسائل الراحة فيها.

                الاسم: %s
                الموديل: %s
                سنة الإنتاج: %s
                عدد الركاب: %s
                اللون: %s
                """.formatted(
                vehicle.getName(),
                vehicle.getModel(),
                vehicle.getProductionYear(),
                vehicle.getVehicleCapacity(),
                vehicle.getColor()
        );

        Response response = client.responses().create(
                ResponseCreateParams.builder()
                        .model("gpt-6-luna")
                        .input(prompt)
                        .build()
        );

        return response.output().stream()
                .flatMap(item -> item.message().stream())
                .flatMap(message -> message.content().stream())
                .flatMap(content -> content.outputText().stream())
                .map(outputText -> outputText.text())
                .findFirst()
                .orElseThrow(() ->
                        new IllegalStateException("AI did not generate a description"));
    }
}