# ASE - Vagrant Box

## Installation

Required packages:

* Vagrant: Installed via homebrew (Mac), or https://docs.vagrantup.com/v2/installation/index.html.
* Virtualbox: https://www.virtualbox.org/wiki/Downloads.

Alternatively Parallels can be used on Mac but it is not free.

Github: https://github.com/alexeiZamyatin/ase-vagrant


## Using the VM

**vagrant up** starts the Vagrant Box. During the first start everything (VM image and provisioning) is downloaded and configured (Grab a coffee or tea ;) ).


**vagrant provision** will starts subsequent provisioning runs and is required when the provisioning files changes. vagrant up does not check this one. 
Alternatively one can **run vagrant destroy; vagrant up** but this will take much longer as everything is installed from scratch.

**vagrant ssh** allows to ssh into the VM. Keep in mind that every change will be lost if you destroy the box. If it is a change that is interesting for the whole team, the puppet provisioning should be adapted. (Ping Dominik or Martin)


## Tesing the Database connection
The supplied .jar file can be used to test the connection to the Postgres database. 

Altenatively, you can implement a database connection snippet yourself using the following data:

* Database name: unipug
* User: unipug
* Password: postgres


### Sources:
https://github.com/jackdb/pg-app-dev-vm
https://wiki.postgresql.org/wiki/PostgreSQL_For_Development_With_Vagrant
