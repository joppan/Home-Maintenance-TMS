aws dynamodb create-table --table-name tickets --attribute-definitions AttributeName=ticketId,AttributeType=S --key-schema AttributeName=ticketId,KeyType=HASH --billing-mode PROVISIONED --provisioned-throughput ReadCapacityUnits=5,WriteCapacityUnits=5 --profile personal --region us-west-2



aws dynamodb create-table --table-name tickets --attribute-definitions AttributeName=ticketId,AttributeType=S --key-schema AttributeName=ticketId,KeyType=HASH --billing-mode PROVISIONED --provisioned-throughput ReadCapacityUnits=5,WriteCapacityUnits=5 --endpoint-url http://localhost:8000


aws dynamodb list-tables --endpoint-url http://localhost:8000    