## Prerequisites
- Docker Compose
## Getting started
1. Create .env like .env.example
```
cp .env.example .env
```
2. Set SMPT server and user credentials in .env
```
MAIL_ENABLE=true
MAIL_HOST=YOUR_HOST
MAIL_PORT=YOUR_PORT
MAIL_USER=YOUR_USER
MAIL_PASSWORD=YOUR_PASSWORD
```
3. Up the cluster
```
docker-compose -f docker-compose.local.yml up
```
4. Check localhost:8080
```
curl -X GET "https://localhost:8080/api/1.0/auth" -H "accept: application/json"
```
If you see the response with 403 status, consider deployment done!