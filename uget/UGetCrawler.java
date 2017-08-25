package net.elenx.epomis.provider.pl.uget;

import lombok.Data;
import net.elenx.epomis.entity.JobOffer;
import net.elenx.epomis.provider.JobOfferProvider;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Service
public class UGetCrawler implements JobOfferProvider
{
    static String REST_URL = "http://jobs.u-get.pro/offers.json";
    static String JAVA_KEY_WORD = "java";

    RestTemplate restTemplate;

    @Override
    public Set<JobOffer> offers()
    {

        return Arrays.stream(restTemplate.getForObject(REST_URL, UGetData[].class))
                     .filter(offer -> offer.getTitle().toLowerCase().contains(JAVA_KEY_WORD))
                     .map(this::extractOffer)
                     .collect(Collectors.toSet());
    }

    private JobOffer extractOffer(UGetData jsonOfferData)
    {
        String title = jsonOfferData.getTitle();
        String href = jsonOfferData.getId();
        String location;

        if(jsonOfferData.isRemote())
        {
            location = "Remote";
        }
        else
        {
            location = jsonOfferData.getAddress();
        }

        return JobOffer.builder()
                       .title(title)
                       .href(href)
                       .location(location)
                       .company("No company given")
                       .build();
    }
}
