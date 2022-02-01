package contracts

import org.springframework.cloud.contract.spec.Contract

[
    Contract.make {
        name("Create subscription")
        request {
            method('POST')
            url("/v0/subscription")
            headers {
                contentType(applicationJson())
            }
            body(
                [
                    "email": "test@test.com",
                    "firstName": "test",
                    "gender": "MALE",
                    "birthdate": new Date(),
                    "consent": true,
                    "newsletterId": 1L
                ]
            )
        }
        response {
            status 201
            body(
                [
                    "timestamp": anyNonBlankString(),
                    "status": 201,
                    "response": anyNumber()
                ]
            )
        }
    },
    Contract.make {
        name("Create subscription should return 400")
        request {
            method('POST')
            url("/v0/subscription")
            headers {
                contentType(applicationJson())
            }
            body(
                [
                    "firstName": "test",
                    "gender": "MALE",
                    "birthdate": new Date(),
                    "consent": true,
                    "newsletterId": 1L
                ]
            )
        }
        response {
            status 400
            body(
                [
                    "timestamp": anyNonBlankString(),
                    "status": 400,
                    "error": "Bad Request",
                    "message": "Email is a required field"
                ]
            )
        }
    }
]


