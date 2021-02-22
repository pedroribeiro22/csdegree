# Handy commands for the classes regarding the usage of docker

## Create a docker container and keep it running in foreground

`
docker run --user 1000:1000 -v /home/pedro/Code/abd/data/:/var/lib/postgresql/data -e POSTGRES_DB=abd -e POSTGRES_USER=abduser -e POSTGRES_PASSWORD=segredo -p 5432:5432 -it -l pgsql postgres:12
`

## Start a created docker in interactive mode

`
docker start -ai <docker_name>
`

## Reload configurations after changing configuration file

`
docker exec -it <docker_name> pg_ctl reload
`

## Execute commands

`
docker exec -it <docker_name> psql -U abduser abd
`
