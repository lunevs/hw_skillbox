package app.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class Contact {

    @Setter
    private String fullName;

    @Setter
    private String phoneNumber;

    private final String email;

}
