# gql-server
Creating a graphql server with java-springboot.
Added support for traceability using zipkin

## Scope
This repository is used to create a graphql server. The idea is to expose queries and mutations for three entities:
- Artists
- Albums
- Tracks

The relationship between the entities is defined below:

![](docs/relationship.png)

## Architecture

![](docs/arch.png)

## Deployment
The project is container ready. To deploy use the steps below

- Change the image tag version in docker-compose.

- Run `sudo docker-compose build`

- Run `sudo docker push prakharsrivastav/gql:<tag>`, to push to docker hub

- Change the image tag version in the `k8s/dployment.yml`

- deploy to kubernetes using `sudo kubectl apply -f k8s/service.yml` and `sudo kubectl apply -f k8s/deployment`


## Communication
![](docs/infra.png)

## Distributed Tracing
Added support for distributed tracing using zipkin backend. This is not implemented for k8s yet, but can be tested using docker-compose.
Added new zipkin UI for visualization.

- docker-compose up -d
- docker-compose up -d (for other linked services)
- navigate to http://localhost:9411 to see the zipkin dashboard. Alternative , watch traces on new zipkin ui on http://localhost:80
- navigate to http://localhost:8080/graphiql to see the graphql dashboard
- send a gql query via dashboard
- observe traces on the zipkin dashboard

## Improvements

- Automated build on commit
- Automated tests