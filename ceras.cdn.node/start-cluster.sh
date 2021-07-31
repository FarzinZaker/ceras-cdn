docker kill $(docker ps -q)

docker container prune -f

docker run -d \
  --name node_000 \
  -p 8000:80 \
  -e name=node_000 \
  -e s3_bucket=ceras-cdn \
  -e s3_root=000/ \
  ceras/ceras.cdn.node:0.1

docker run -d \
  --name node_100 \
  -p 8100:80 \
  -e name=node_100 \
  -e s3_bucket=ceras-cdn \
  -e s3_root=000/ \
  -e parent_url=http://172.17.0.2/ \
  ceras/ceras.cdn.node:0.1

docker run -d \
  --name node_200 \
  -p 8200:80 \
  -e name=node_200 \
  -e s3_bucket=ceras-cdn \
  -e s3_root=200/ \
  -e parent_url=http://172.17.0.2/ \
  ceras/ceras.cdn.node:0.1

docker run -d \
  --name node_110 \
  -p 8110:80 \
  -e name=node_110 \
  -e s3_bucket=ceras-cdn \
  -e s3_root=110/ \
  -e parent_url=http://172.17.0.3/ \
  ceras/ceras.cdn.node:0.1

docker run -d \
  --name node_120 \
  -p 8120:80 \
  -e name=node_120 \
  -e s3_bucket=ceras-cdn \
  -e s3_root=120/ \
  -e parent_url=http://172.17.0.3/ \
  ceras/ceras.cdn.node:0.1

docker run -d \
  --name node_210 \
  -p 8210:80 \
  -e name=node_210 \
  -e s3_bucket=ceras-cdn \
  -e s3_root=210/ \
  -e parent_url=http://172.17.0.4/ \
  ceras/ceras.cdn.node:0.1

docker run -d \
  --name node_220 \
  -p 8220:80 \
  -e name=node_220 \
  -e s3_bucket=ceras-cdn \
  -e s3_root=220/ \
  -e parent_url=http://172.17.0.4/ \
  ceras/ceras.cdn.node:0.1