## Prerequisites
- Docker Compose
## Getting started
1. Create .env like .env.example
```
cp .env.example .env
```
2. Up the cluster
```
docker-compose -f docker-compose.local.yml up
```
3. Check localhost:8080
```
curl -X GET "https://localhost:8080/api/1.0/auth" -H "accept: application/json"
```
If you see the response with 403 status, consider deployment done!