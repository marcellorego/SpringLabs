docker run --name mongo-service-db \
    -p 27017:27017 \
    -v /Users/mgr/web/git/SpringLabs/lesson13/mongo-service/docker/data/db:/data/db \
    -d mongo:latest \
    --logpath /var/log/mongodb/mongo.log