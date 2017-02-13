# Blockchain Data Miner

A tool for extracting data from Bitcoin-like blockchains into a relational model. 

## Requirements:

+ Maven > 3.3.9
+ Java 1.8
+ A running client of the blockchain you want to extract data from (e.g. Bitcoin)

## Usage

1. Clone this repository
2. In the parent folder, run ``` mvn clean install ```. This will download and build all necessary modules. Requires internet access!
3. Configure the profile you want to use (see ...)
4. Execute ``` mvn spring-boot:run -Drun.profiles=<your profile> ```

Depending on the configuration and the size of the chosen blockchain, the initial data extraction process can take up to 96 hours (or longer).
The tool provides a performance monitoring, which will output information on operation duration (blockchain client queries and persistence operations) to stdout. 
Termination will happen automatically after sucessfull extraction or in case of an error (e.g. blockchain client dies). 

An email notification can be sent to your email address, if your server provides a mail server (see ...)

## Configuration
Currently supported profiles: 

+ Bitcoin - ```btc```
+ Namecoin - ```nmc```
+ Litecoin - ```ltc```
+ Dogecoin - ```doge```


