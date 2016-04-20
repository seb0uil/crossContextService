#What to do
After compile and package maven projects, just copy the 3 files here before build docker image.

#Compile docker image
just
```
docker build -t seb0uil/crossContextService .
```

After that, you can start the container using
```
docker run -p 8080:8080 seb0uil/crossContextService
```

Then, you can test it on 
```
http://127.0.0.1:8080/crossContextClient
```
