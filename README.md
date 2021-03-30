# poseidon

LIST OF ENDPOINTS

EndPoints that doesn't require Authorization and Authentication:
 
 - "localhost:8080/"
 - "localhost:8080/user/add" 
 - "localhost:8080/login"
 - "localhost:8080/user/validate" 

EndPoints that require Authentication :
 
 - "localhost:8080/bidList/list" - "localhost:8080/bidList/add" - "localhost:8080/bidList/validate" - "localhost:8080/bidList/update/{id}" -                            "localhost:8080/bidList/delete/{id}"
 
 - "localhost:8080/rating/list" - "localhost:8080/rating/add" - "localhost:8080/rating/validate" - "localhost:8080/rating/update/{id}"                                  "localhost:8080/rating/delete/{id}"
 
 - "localhost:8080/ruleName/list" - "localhost:8080/ruleName/add" - "localhost:8080/ruleName/validate" - "localhost:8080/ruleName/update/{id}" -                        "localhost:8080/ruleName/delete/{id}"
 
 - "localhost:8080/trade/list" - "localhost:8080/trade/add" - "localhost:8080/trade/validate" - "localhost:8080/trade/update/{id}" -                                    "localhost:8080/trade/delete/{id}"
 
 - "localhost:8080/curvePoint/list" - "localhost:8080/curvePoint/add" - "localhost:8080/curvePoint/validate" - "localhost:8080/curvePoint/update/{id}"  -              "localhost:8080/curvePoint/delete/{id}" 

EndPoints that require Authorization:
 
 - localhost:8080/admin/home"
 
 - "localhost:8080/secure/article-details"
 
 - "localhost:8080/user/update{id}" - "localhost:8080/user/delete/{id}"
