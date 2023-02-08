package cd.bensmile.hoaxify.errors;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
public class ApiError {

    private long timeStamp = Instant.now().toEpochMilli();
    private int status;
    private String message;
    private String url;
    private Map<String, String> validationErrors ;

    public ApiError(int status, String message, String url) {
        super();
        this.status = status;
        this.message = message;
        this.url = url;
    }
}
