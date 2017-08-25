package net.elenx.epomis.provider.pl.uget

import net.elenx.epomis.entity.JobOffer
import org.springframework.web.client.RestTemplate
import spock.lang.Specification

class UGetCrawlerTest extends Specification {

    void "extract JobOffers"() {
        given:
        UGetData[] offers = [
            UGetData.builder()
                .id("a3a5f5c9-4059-4840-a9ad-f05bac4ad125")
                .title("Java Developer")
                .remote(true)
                .build(),

            UGetData.builder()
                .id("f52d4c3f-92f1-48ab-80fb-9e4b9af398d3")
                .title("Junior Java Developer")
                .remote(false)
                .build()
        ]

        offers[1].address = new UGetData.Address("Warszawa")

        RestTemplate restTemplate = Mock();
        UGetCrawler uGetCrawler = new UGetCrawler(restTemplate)

        restTemplate.getForObject(_, _) >> offers


        when:
        Set<JobOffer> offerSet = uGetCrawler.offers()

        then:
        offerSet.size() == 2
    }
}
