[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-24ddc0f5d75046c5622901739e7c5dd533143b0c8e959d652212380cedb1ea36.svg)](https://classroom.github.com/a/hqM0T4I-)
[![Open in Visual Studio Code](https://classroom.github.com/assets/open-in-vscode-718a45dd9cf7e7f842a935f5ebbe5719a5e09af4491e668f4dbf3b35d5cca122.svg)](https://classroom.github.com/online_ide?assignment_repo_id=13628945&assignment_repo_type=AssignmentRepo)
## CSC435 Programming Assignment 2 (Winter 2024)
**Jarvis College of Computing and Digital Media - DePaul University**

**Student**: Zinan Yang  (email : zyang50@depaul.edu) 
**Solution programming language**: Java

### Requirements

If you are implementing your solution in C++ you will need to have GCC 12.x and CMake 3.22.x installed on your system. On Ubuntu 22.04 you can install GCC and set it as default compiler using the following commands:

```
sudo apt install g++-12 gcc-12 cmake
sudo update-alternatives --install /usr/bin/gcc gcc /usr/bin/gcc-11 110
sudo update-alternatives --install /usr/bin/gcc gcc /usr/bin/gcc-12 120
sudo update-alternatives --install /usr/bin/g++ g++ /usr/bin/g++-11 110
sudo update-alternatives --install /usr/bin/g++ g++ /usr/bin/g++-12 120
```

If you are implementing your solution in Java you will need to have Java 1.7.x and Maven 3.6.x installed on your systems. On Ubuntu 22.04 you can install Java and Maven using the following commands:

```
sudo apt install openjdk-17-jdk maven

```

### Setup

There are 5 datasets (Dataset1, Dataset2, Dataset3, Dataset4, Dataset5) that you need to use to evaluate your solution. Before you can evaluate your solution you need to download the datasets. You can download the datasets from the following link:

https://depauledu-my.sharepoint.com/:f:/g/personal/aorhean_depaul_edu/EgmxmSiWjpVMi8r6QHovyYIB-XWjqOmQwuINCd9N_Ppnug?e=TLBF4V

After you finished downloading the datasets copy them to the dataset directory (create the directory if it does not exist). Here is an example on how you can copy Dataset1 to the remote machine and how to unzip the dataset:

```
remote-computer$ mkdir datasets
local-computer$ scp Dataset1.zip cc@<remote-ip>:<path-to-repo>/datasets/.
remote-computer$ cd <path-to-repo>/datasets
remote-computer$ unzip Dataset1.zip
```

### C++ solution
#### How to build/compile

To build the C++ solution use the following commands:
```
cd app-cpp
mkdir build
cmake -S . -B build
cmake --build build
```

#### How to run application

To run the C++ solution (after you build the project) use the following command:
```
./build/file-retrieval-engine
> <index | search | quit>
```

#### Example

```
./build/file-retrieval-engine
> index ../datasets/Dataset1
Completed indexing in 10.386 seconds
> search Worms
Search completed in 2.8 seconds
Search results (top 10):
* folder6/document200.txt 11
* folder14/document417.txt 4
* folder6/document424.txt 4
* folder11/document79.txt 1
* folder12/document316.txt 1
* folder13/document272.txt 1
* folder13/document38.txt 1
* folder15/document351.txt 1
* folder1/document260.txt 1
* folder4/document101.txt 1
> search distortion AND adaptation
Search completed in 3.27 seconds
Search results (top 10):
* folder6/document200.txt 57
* folder7/document476.txt 5
* folder13/document38.txt 4
* folder6/document408.txt 3
* folder7/document298.txt 3
* folder10/document107.txt 2
* folder10/document206.txt 2
* folder10/document27.txt 2
* folder14/document145.txt 2
* folder15/document351.txt 2
> quit
```

### Java solution
#### How to build/compile

To build the Java solution use the following commands:
```
cd /home/zinan/Downloads/csc435-pa2-ZinanYANG-main/app-java
mvn clean install
mvn compile
mvn package
```

#### How to run application

To run the Java solution (after you build the project) use the following command:
```
java -cp target/app-java-1.0-SNAPSHOT.jar csc435.app.FileRetrievalEngine
```

#### Example

```
java -cp target/app-java-1.0-SNAPSHOT.jar csc435.app.FileRetrievalEngine
> index /home/zinan/Datasets/Dataset1
Successfully read file: document230.txt
Successfully read file: document69.txt
Successfully read file: document385.txt
Successfully read file: document456.txt
Successfully read file: document246.txt
Successfully read file: document262.txt
Successfully read file: document355.txt
Successfully read file: document53.txt
...
Successfully read file: document348.txt
Successfully read file: document207.txt
All indexing tasks have completed.
Indexing completed for: /home/zinan/Datasets/Dataset1 in 8.88 seconds

> search Worms
Original query: 'Worms'
Split query into terms: [worms]
Processing term: 'worms'

Lookup for term 'worms': {document270.txt=6, document113.txt=1, document164.txt=1, document351.txt=1, document318.txt=1, document316.txt=1, document407.txt=1, document430.txt=4, document416.txt=1, document163.txt=3, document200.txt=17, document350.txt=1, document172.txt=1, document103.txt=1, document10.txt=8, document450.txt=3, document349.txt=2, document480.txt=1, document79.txt=2, document286.txt=14, document260.txt=3, document22.txt=2, document101.txt=1, document35.txt=1, document16.txt=1, document348.txt=1, document272.txt=1, document17.txt=2, document144.txt=2, document488.txt=2, document240.txt=1, document77.txt=1, document444.txt=1, document232.txt=2, document312.txt=2, document160.txt=1, document258.txt=1, document401.txt=1, document51.txt=2, document452.txt=1, document292.txt=1, document33.txt=1, document461.txt=2, document443.txt=2, document125.txt=2, document27.txt=2, document133.txt=1, document291.txt=3, document265.txt=2, document397.txt=1, document32.txt=1, document107.txt=2, document417.txt=4, document111.txt=3, document124.txt=6, document307.txt=1, document344.txt=1, document472.txt=1, document418.txt=1, document106.txt=1, document330.txt=1, document424.txt=6, document166.txt=2, document259.txt=1, document38.txt=3, document90.txt=1, document440.txt=1, document122.txt=2, document313.txt=1, document326.txt=1, document30.txt=8}


Results for term 'worms': {document270.txt=6, document113.txt=1, document164.txt=1, document351.txt=1, document318.txt=1, document316.txt=1, document407.txt=1, document430.txt=4, document416.txt=1, document163.txt=3, document200.txt=17, document350.txt=1, document172.txt=1, document103.txt=1, document10.txt=8, document450.txt=3, document349.txt=2, document480.txt=1, document79.txt=2, document286.txt=14, document260.txt=3, document22.txt=2, document101.txt=1, document35.txt=1, document16.txt=1, document348.txt=1, document272.txt=1, document17.txt=2, document144.txt=2, document488.txt=2, document240.txt=1, document77.txt=1, document444.txt=1, document232.txt=2, document312.txt=2, document160.txt=1, document258.txt=1, document401.txt=1, document51.txt=2, document452.txt=1, document292.txt=1, document33.txt=1, document461.txt=2, document443.txt=2, document125.txt=2, document27.txt=2, document133.txt=1, document291.txt=3, document265.txt=2, document397.txt=1, document32.txt=1, document107.txt=2, document417.txt=4, document111.txt=3, document124.txt=6, document307.txt=1, document344.txt=1, document472.txt=1, document418.txt=1, document106.txt=1, document330.txt=1, document424.txt=6, document166.txt=2, document259.txt=1, document38.txt=3, document90.txt=1, document440.txt=1, document122.txt=2, document313.txt=1, document326.txt=1, document30.txt=8}


Intersection of all terms: {document270.txt=6, document113.txt=1, document164.txt=1, document351.txt=1, document318.txt=1, document316.txt=1, document407.txt=1, document430.txt=4, document416.txt=1, document163.txt=3, document200.txt=17, document350.txt=1, document172.txt=1, document103.txt=1, document10.txt=8, document450.txt=3, document349.txt=2, document480.txt=1, document79.txt=2, document286.txt=14, document260.txt=3, document22.txt=2, document101.txt=1, document35.txt=1, document16.txt=1, document348.txt=1, document272.txt=1, document17.txt=2, document144.txt=2, document488.txt=2, document240.txt=1, document77.txt=1, document444.txt=1, document232.txt=2, document312.txt=2, document160.txt=1, document258.txt=1, document401.txt=1, document51.txt=2, document452.txt=1, document292.txt=1, document33.txt=1, document461.txt=2, document443.txt=2, document125.txt=2, document27.txt=2, document133.txt=1, document291.txt=3, document265.txt=2, document397.txt=1, document32.txt=1, document107.txt=2, document417.txt=4, document111.txt=3, document124.txt=6, document307.txt=1, document344.txt=1, document472.txt=1, document418.txt=1, document106.txt=1, document330.txt=1, document424.txt=6, document166.txt=2, document259.txt=1, document38.txt=3, document90.txt=1, document440.txt=1, document122.txt=2, document313.txt=1, document326.txt=1, document30.txt=8}


document200.txt 17
document286.txt 14
document10.txt 8
document30.txt 8
document270.txt 6
document124.txt 6
document424.txt 6
document430.txt 4
document417.txt 4
document163.txt 3


> search distortion AND adaptation
Original query: 'distortion AND adaptation'
Split query into terms: [distortion, adaptation]
Processing term: 'distortion'

Lookup for term 'distortion': {document162.txt=1, document53.txt=1, document77.txt=1, document107.txt=1, document450.txt=1, document66.txt=1, document457.txt=1, document139.txt=1, document476.txt=2, document206.txt=1, document351.txt=1, document145.txt=1, document22.txt=7, document305.txt=1, document216.txt=1, document38.txt=3, document298.txt=1, document284.txt=1, document27.txt=1, document62.txt=1, document200.txt=10, document408.txt=2}
Results for term 'distortion': {document162.txt=1, document53.txt=1, document77.txt=1, document107.txt=1, document450.txt=1, document66.txt=1, document457.txt=1, document139.txt=1, document476.txt=2, document206.txt=1, document351.txt=1, document145.txt=1, document22.txt=7, document305.txt=1, document216.txt=1, document38.txt=3, document298.txt=1, document284.txt=1, document27.txt=1, document62.txt=1, document200.txt=10, document408.txt=2}

Lookup for term 'adaptation': {document77.txt=1, document444.txt=1, document292.txt=1, document351.txt=1, document465.txt=1, document33.txt=1, document448.txt=2, document461.txt=1, document474.txt=17, document168.txt=1, document252.txt=3, document201.txt=1, document27.txt=1, document200.txt=45, document477.txt=5, document67.txt=2, document404.txt=3, document291.txt=1, document235.txt=1, document408.txt=1, document97.txt=1, document473.txt=1, document107.txt=1, document132.txt=1, document472.txt=1, document145.txt=1, document216.txt=1, document298.txt=1, document38.txt=1, document475.txt=3}
Results for term 'adaptation': {document77.txt=1, document444.txt=1, document292.txt=1, document351.txt=1, document465.txt=1, document33.txt=1, document448.txt=2, document461.txt=1, document474.txt=17, document168.txt=1, document252.txt=3, document201.txt=1, document27.txt=1, document200.txt=45, document477.txt=5, document67.txt=2, document404.txt=3, document291.txt=1, document235.txt=1, document408.txt=1, document97.txt=1, document473.txt=1, document107.txt=1, document132.txt=1, document472.txt=1, document145.txt=1, document216.txt=1, document298.txt=1, document38.txt=1, document475.txt=3}

Intersection of all terms: {document77.txt=2, document107.txt=2, document351.txt=2, document145.txt=2, document216.txt=2, document38.txt=4, document298.txt=2, document27.txt=2, document200.txt=55, document408.txt=3}

document200.txt 55
document38.txt 4
document408.txt 3
document77.txt 2
document107.txt 2
document351.txt 2
document145.txt 2
document216.txt 2
document298.txt 2
document27.txt 2

> quit
zinan@zinan:~/Downloads/csc435-pa2-ZinanYANG-main/app-java$
```
