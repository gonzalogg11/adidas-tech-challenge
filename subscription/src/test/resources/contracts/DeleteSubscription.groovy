package contracts

import org.springframework.cloud.contract.spec.Contract

[
    Contract.make {
        name("Delete subscription")
        request {
            method('DELETE')
            url("/v0/subscription/1")
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
                    "response": "OK"
                ]
            )
        }
    },
    Contract.make {
        name("Delete subscription should return 400")
        request {
            method('DELETE')
            url("/v0/subscription/99")
            headers {
                contentType(applicationJson())
            }
        }
        response {
            status 400
            body(
                    [
                            "timestamp": anyNonBlankString(),
                            "status": 400,
                            "error": "Bad Request",
                            "message": "Subscription with id 99 does not exist"
                    ]
            )
        }
    },
]


