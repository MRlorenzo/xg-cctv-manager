fetch('sysUser/login', {method:'POST' ,body:JSON.stringify({}),headers: {
        "Content-Type": "application/json"
    }})


fetch('sysUser/jwtLogin', {method:'POST' ,body:JSON.stringify({}),headers: {
        "Content-Type": "application/json"
    }})

fetch('jsException/getJsExceptionPageList', {
    method:'GET',headers: {
        "Authorization": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1Nzk3MjI3ODQsInVzZXJuYW1lIjoiYWRtaW4ifQ.CFnvimIbNFNVAAlLOa9F8BmKaIeFgydwo5jy8Ga4k2I"
    }})


fetch('sysUser/logout', {  method:'GET'})