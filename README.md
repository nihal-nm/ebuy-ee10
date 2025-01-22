[![Build Status](https://v3.travis.ibm.com/was-svt/ebuy-ee10.svg?token=mGqtDhji633qrPtvXK8V&branch=main)](https://v3.travis.ibm.com/was-svt/ebuy-ee10)

### Here are the steps for deployment on OCP cluster

- Get the ebuy liberty project on your local ocp client machine. The ebuy OpenLiberty project is located at:  https://github.ibm.com/tkulik-cp4apps420/eBuy

`git clone git@github.ibm.com:tkulik-cp4apps420/eBuy.git`

In this example we are going to use `Peroni` OCP cluster.

- On the client machine run the login cmd to `Peroni` cluster:

`oc login --token=sha256~QHwNzyrldSrL0c__EnVqtIuJ4Nab3vl6yXF95cv1m5I --server=https://api.peroni.cp.fyre.ibm.com:6443`

- run `docker-login.sh` script to login to docker.   This script `docker-login.sh` can be found in Dan's git project `ocp-on-fyre`. 

- run the following cmds to create ebuy docker image and push it to Peroni image registry. Here `ebuy-test` is the name of the image. 

1. `docker build --no-cache -t  ebuy-test .`

2.  `docker tag  <image_id>   default-route-openshift-image-registry.apps.peroni.cp.fyre.ibm.com/nest-ocp-ebuy/ebuy-test`

Here `nest-ocp-ebuy` is the namespace created in OCP.

3. `docker push default-route-openshift-image-registry.apps.peroni.cp.fyre.ibm.com/nest-ocp-ebuy/ebuy-test`

4. edit `application-cr.yaml` under `/eBuy/operator/application` to change the images name to `ebuy_test`

5. Run cmd to deploy ebuy image using OpenLiberty Operator:

`oc apply -f application-cr.yaml`

`application-cr.yaml` is using OpenLiberty Operator to deploy the app.



### Here is the instructions on how to deploy ebuy app on OpenShift cluster using the image from Artifactory

Also, here we are going to use `app-deploy.yaml` file instead of `application-cr.yaml`,  in which we are passing the variables for db2 server, db user and password.  Their values should overwrite the values in the server.xml file. 

1. Use `operator/application/app-deploy.yaml` file for eBuy deployment using Open Liberty Operator. It includes the variables for db. 

2. Run this cmd to create the secrete for the db user password. Make sure you create it in the same `ebuy-test` namespace, where the deployment will be created. 

```
oc create secret generic db-credential --from-file=./dbpw
```
And you can see this secret in the OCP UI:

![image](https://media.github.ibm.com/user/18557/files/f14d1980-ebb2-11eb-9343-6f0d1bfbb714)

3. To be able to pull this image `docker-na-public.artifactory.swg-devops.com/hyc-wassvt-team-image-registry-docker-local/svtapps/nest-ebuy-ol21007` from the artifactory you need to setup the pull-secret on your OCP cluster by adding your ID and pwd for this artifactory. 

In OCP Console go to Workloads -> Secrets -> pull-secret  under `openshift-config` project. Under Actions pull-down menu select `Edit secret`

![image](https://media.github.ibm.com/user/18557/files/2b0a7f00-cf7a-11eb-9a5a-d417000175a2)

And add your credentials for the Artifactory registry:

![image](https://media.github.ibm.com/user/18557/files/e92e0880-cf7a-11eb-9446-48903a8fdb0a)


4. Login to Peroni cluster and move to `ebuy-test` project where I want to deploy ebuy app. 

```
root@stark-client:/ebuy_artifactory_app_deploy# oc login --token=sha256~04uDZK5mCg97Qw5qw0sUOD4QkY0pRIl_O-zJPcX_v-M --server=https://api.peroni.cp.fyre.ibm.com:6443
Logged into "https://api.peroni.cp.fyre.ibm.com:6443" as "tkulik" using the token provided.

You have access to 74 projects, the list has been suppressed. You can list all projects with 'oc projects'

Using project "ebuy-test".
```

5.  Run the cmd to deploy ebuy:

```
 oc apply -f app-deploy.yaml
```
And deployment is created , pod is running.  

6. Go to ebuy app route:

![image](https://media.github.ibm.com/user/18557/files/e64eb500-ebc3-11eb-97af-673973acab6c)

![image](https://media.github.ibm.com/user/18557/files/067e7400-ebc4-11eb-8f6e-bafee6ebbd27)


7. Click on Enter eBuy and the next page opens:

![image](https://media.github.ibm.com/user/18557/files/52c9b400-ebc4-11eb-8d24-084f8c7f8cb1)

This proves that db connection is working, and I was able to connect to the new db,  so that means that the variable in app-deploy.yaml file has overwritten the value in the server.xml inside the image. 

8. I verified  that a new db2 server, specified in `app-deploy.yaml` file was used. Registered a new user using ebuy GUI,  tanya102, and then looked up this user on the new db server:

```
db2 "select * from ebuy.order where username = 'tanya102' "

[db2inst1@barbara-ebuy-db2-1 ~]$ db2 "select * from ebuy.order where username = 'tanya102' "

ID                   ISBN        QUANTITY    USERNAME                                                                                                                                                                                                                                                       TIME                       ISPAID
-------------------- ----------- ----------- -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- -------------------------- -----------
            69235205    19837674           1 tanya102                                                                                                                                                                                                                                                       2021-07-22-16.12.55.775000           1

  1 record(s) selected.h
```

To continue with ebuy application, login as user100, with password puser100:

![image](https://media.github.ibm.com/user/18557/files/4ec42480-be15-11eb-8b74-6a15e2045479)

![image](https://media.github.ibm.com/user/18557/files/70251080-be15-11eb-8fbc-99f4d7e45263)

The db has users starting with user1/puser1,  user2/puser2, ...  etc.,   up to user1000/puser1000. 

Add items to cart and proceed:

![image](https://media.github.ibm.com/user/18557/files/90ed6600-be15-11eb-8863-2ceeb650a305)

Pay and confirm your payment:

![image](https://media.github.ibm.com/user/18557/files/c72ae580-be15-11eb-8284-56e49256c21b)

Then you can logout and log back in as a diff user

![image](https://media.github.ibm.com/user/18557/files/0a855400-be16-11eb-8a8f-e804ef217713)



