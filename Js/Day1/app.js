
const fs = require('fs');
const readline = require('readline');

async function processLineByLine() {

    var sum = 0;
    var max = 0;

    const fileStream = fs.createReadStream("input.txt");

    const r = readline.createInterface({
        input : fileStream
        });

    for await (const line of r) {
        
        if(line=="") {
            if(max < sum)
                max = sum
            sum = 0;
        }
        else {
            sum += parseInt(line);
        }
    }

    console.log(max);
   
}

processLineByLine();




