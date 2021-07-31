
aws ecr get-login-password --region us-east-1 | docker login --username AWS --password-stdin 559773491232.dkr.ecr.us-east-1.amazonaws.com
docker pull 559773491232.dkr.ecr.us-east-1.amazonaws.com/ceras-cdn:latest

