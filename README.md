# Charter Rewards API

### Build : ./gradlew clean build
### Deploy : Run RewardsApplication.java 
### Validate:(as below)
### End point : GET http://localhost:52415/v1/customers/rewards
### Sample Response(Success):
``` json
[
    {
        "customer_id": "3456",
        "points_by_month": [
            {
                "month": "FEBRUARY",
                "points": 501.0
            }
        ],
        "total_points": 501.0
    },
    {
        "customer_id": "1234",
        "points_by_month": [
            {
                "month": "FEBRUARY",
                "points": 72.0
            },
            {
                "month": "JANUARY",
                "points": 94.0
            }
        ],
        "total_points": 166.0
    },
    {
        "customer_id": "7896",
        "points_by_month": [
            {
                "month": "DECEMBER",
                "points": 0.0
            }
        ],
        "total_points": 0.0
    }
]
```

### Sample Request(By Customer Id) :
### End point : GET http://localhost:52415/v1/customers/1234/rewards
### Sample Response(Success):
```json
[
  {
    "customer_id": "1234",
    "points_by_month": [
      {
        "month": "FEBRUARY",
        "points": 72.0
      },
      {
        "month": "JANUARY",
        "points": 94.0
      }
    ],
    "total_points": 166.0
  }
]
```
