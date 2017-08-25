package net.elenx.epomis.provider.pl.uget;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.NonFinal;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@RequiredArgsConstructor(staticName = "label")
@AllArgsConstructor(access = AccessLevel.PROTECTED)
class UGetData
{
    @JsonProperty
    @NonFinal
    String id;
    @JsonProperty
    @NonFinal
    String title;
    @JsonProperty
    @NonFinal
    boolean remote;
    @JsonProperty
    @NonFinal
    Address address;

    public String getAddress()
    {
        return address.getLabel();
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    @NoArgsConstructor
    public static class Address
    {
        @NonFinal
        String label;

        public Address(String label)
        {
            this.label = label;
        }

    }
}