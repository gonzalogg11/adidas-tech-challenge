package contracts

import org.springframework.cloud.contract.spec.Contract

[
    Contract.make {
        name("Get single subscription")
        request {
            method('GET')
            url("/v0/subscription?ids=1")
            headers {
                contentType(applicationJson())
            }
        }
        response {
            status 200
            body(
                    [
                        "timestamp": anyNonBlankString(),
                        "status": 200,
                        "response": [
                            [
                                "id": anyNumber(),
                                "email": "test1@test.com",
                                "firstName": anyNonBlankString(),
                                "gender": anyNonBlankString(),
                                "birthdate": anyNonBlankString(),
                                "consent": anyBoolean(),
                                "newsletterId": anyNumber()
                            ]
                        ]
                    ]
            )
        }
    },
    Contract.make {
        name("Get subscriptions")
        request {
            method('GET')
            url("/v0/subscription?ids=1,2")
            headers {
                contentType(applicationJson())
            }
        }
        response {
            status 200
            body(
                    [
                        "timestamp": anyNonBlankString(),
                        "status": 200,
                        "response": [
                            [
                                "id": anyNumber(),
                                "email": "test1@test.com",
                                "firstName": anyNonBlankString(),
                                "gender": anyNonBlankString(),
                                "birthdate": anyNonBlankString(),
                                "consent": anyBoolean(),
                                "newsletterId": anyNumber()
                            ],
                            [
                                "id": anyNumber(),
                                "email": "test2@test.com",
                                "firstName": anyNonBlankString(),
                                "gender": anyNonBlankString(),
                                "birthdate": anyNonBlankString(),
                                "consent": anyBoolean(),
                                "newsletterId": anyNumber()
                            ]
                        ]
                    ]
            )

        }
    },
    Contract.make {
        name("Get subscriptions should return 204")
        request {
            method('GET')
            url("/v0/subscription?ids=3")
            headers {
                contentType(applicationJson())
            }
        }
        response {
            status 204
        }
    }
]


