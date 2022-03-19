# Thermomether Application

## Dependencies & Building

Depending on your OS, you will need to install the [Docker](https://docs.docker.com/) and [docker-compose cli](https://docs.docker.com/compose/install/). 

This version already has the maven wrapper so before execute the whole application insert the aws keys (`access_key` and `secret_key` in `Application.java`) and then execute the script `build.sh` to generate the executable jar.

```bash
$ ./build.sh
```

## Run the application

Simply go to the root folder of the application and enter the following command:

```bash
$ docker-compose up -d 
```

The containers will load and then you can check your local environment in your browser to interact with the application `localhost:8080`

## Run the frontend

You can see the step-by-step guide [here](/frontend/README.md)