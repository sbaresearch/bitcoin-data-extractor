# Blockchain Data Miner

A tool for extracting data from Bitcoin-like blockchains into a relational model. 

## Requirements:

+ Maven > 3.3.9
+ Java 1.8
+ A running client of the blockchain you want to extract data from (e.g. Bitcoin)

## Usage

1. Clone this repository
2. In the parent folder, run ``` mvn clean install ```. This will download and build all necessary modules. Requires internet access!
3. Configure the profile you want to use (see [configuration](#configuration))
4. Execute ``` mvn spring-boot:run -Drun.profiles=<your profile> ```

Depending on the configuration and the size of the chosen blockchain, the initial data extraction process can take up to 96 hours (or longer).
The tool provides a performance monitoring, which will output information on operation duration (blockchain client queries and persistence operations) to stdout. 
Termination will happen automatically after sucessfull extraction or in case of an error (e.g. blockchain client dies). 

An email notification can be sent to your email address, if your server provides a mail server (see [configuration](#configuration))

## Configuration
Currently supported profiles: 

+ Bitcoin - ```btc```
+ Namecoin - ```nmc```
+ Litecoin - ```ltc```
+ Dogecoin - ```doge```

Configuration is possible through .yml files:
+ *spring.datasource*: database information
+ *spring.jpa*: OR Mapping configuration (change jpa.properties.hibernate.dialect to the database you want to use. Currently: PostgreSQL 9.x)
+ *server.port*: Port the server is tomcat started on (necessary for REST API)
+ *chain*:
 + *firstBlockHash*: (SHA256) Blockhash - hash of the first block to be extracted. For new runs set to genesisBlock. Otherwise, set this to the blockhash of the last extracted block, in order to update an existing database.
 + *firstBlockHeight*: int - height of the first block to be extracted. For new runs set to 0. If you want to update an existing database, simply set this to the max. blockheight of the previously extracted data
 + *genesisBlock*: (SHA256) Blockhash - hash of the genesis block of the blockchain
 + *blockQuerySize*: int - amout of blocks extracted with each call to the blockchain client. *Recommended: 1000*. Attention: too large block query size will lead to a timeout of most blockchain client. 
 + *testRun*: true|false - a test run will simply try to extract the first 10 blocks of the chain
 + *runId*: [DEPRECATED] -1 to continue last run, or specify specific run to resume it 
 + *newRun*: true|false - determines whether this is a new run, or an update of an existing database
 + *lightRun*: false|false - Enable/Disable extraction of ALL transactions. Attention: extracting all transactions will lead to a very large database size (~ 3-4 times the size of the original blockchain)
 + *extractNames*: true|false - only used in Namecoin. Activates extraction of Namecoin names

+ *email*: allows to configure email notifications on termination (success / error)
+ *rest.url*: determines the url of the REST API of the blockchain client
+ *jsonrpc*: configuration of the JSON-RPC API of the blockchain client. Not all clients provide a full REST API, so some data may need to be extracted using the old api.
+ *logging*: configuration of log-levels and logfiles

## Vagrant
For testing purposes a Vagrant VM build script is provided in the ```server``` folder. 
Requirements: 
+ Oracle Virtual Box 5.x (or Parallels on Mac)
+ Vagrant

After intalling the necessary software, run ```vagrant up``` in ther server folder. 

As a result, a VM with a PostrgeSQL 9.3 database server running on standard port 5432 will be provisioned. 
You can access the VM by calling ```vagrant ssh``` in the server folder. 

To configure the VM please check the Vagrantfile 
For database configurations (*necessary!!!*) please check the the bootstrap.sh file in the Vagrant-Setup folder. 

Destory the VM - ```vagrant destroy```
Shutdonw VM - ```vagrant halt```

