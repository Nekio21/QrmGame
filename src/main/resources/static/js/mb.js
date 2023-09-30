var client = null;

const machine1TMBS = document.getElementById("maszyna1TMB");
const machine2TMBS = document.getElementById("maszyna2TMB");
const machine3TMBS = document.getElementById("maszyna3TMB");
const machine4TMBS = document.getElementById("maszyna4TMB");

const saldoDiv = document.getElementById("saldoInfo");
const dayDiv = document.getElementById("dayInfo");

let dayFromStart = 0;

function connect(){
    client = Stomp.client("ws:/localhost:8080/drzwi");
    client.connect({}, function (cos){
        sendInit();

        client.subscribe("/mb/informacje", function (message){
            console.log(JSON.parse(message.body));
            doIt(JSON.parse(message.body));
        });
    });
}

function sendDay(ct){
    dayFromStart = ct.dataset.order-1;
    let dayElements = document.getElementsByClassName("calendarTile");
    for(let i=0; i<dayElements.length; i++){
        dayElements[i].classList.remove("calendarTileChoose");
    }

    ct.classList.add("calendarTileChoose");

    client.send("/app/drzwi", {}, JSON.stringify({giveOrTake: "take", order: "MACHINE_PLAN", addInfo: dayFromStart}));
}

function sendGiveMeMagazineInfo(){
     client.send("/app/drzwi", {}, JSON.stringify({giveOrTake: "give", order: "MAGAZINE"}));
}

function sendInit(){
    client.send("/app/drzwi", {}, JSON.stringify({giveOrTake: "take", order: "INIT"}));
}

function sendCheck(){
    client.send("/app/drzwi", {}, JSON.stringify({giveOrTake: "take", order: "CHECK"}));
}

function sendChangeMachinePlan(machineNumber, start, end, letters){
    client.send("/app/drzwi",{}, JSON.stringify({
        giveOrTake: "give",
        order:"MACHINE_PLAN",
        addInfoList:"",
        machinePlanChange: {
            machineNumber: machineNumber,
            start: start,
            end: end,
            letters: letters,
            nrOfDay: dayFromStart
        }
      }));
}

function doIt(gift){

    if(gift.kodOdpowiedzi == "MACHINE_PLAN"){
        machinePlanMethod(gift);
    }
    else if(gift.kodOdpowiedzi == "INIT"){
        initMethod(gift);
    }
    else if(gift.kodOdpowiedzi == "MAGAZINE"){
        console.log("witam");
        magazineUpdate(gift);
    }

}

function initMethod(gift){

    for(let i=-1;i<6;i++){
        var dzionek = (gift.day + i)%7 + 1;

        var calendarTile = document.getElementById("day" + dzionek);

        if(i == -1){
            calendarTile.classList.add("calendarTileChoose");
        }

        calendarTile.style.order = i+2;
        calendarTile.dataset.order = i+2;
    }

    saldoDiv.innerHTML = "Saldo: " + gift.saldo + " PLN";
    dayDiv.innerHTML = "Dzień: " + gift.data;
}

function machinePlanMethod(gift){

    const machine1TMB = machine1TMBS.getElementsByClassName("tileMachineBox");
    const machine2TMB = machine2TMBS.getElementsByClassName("tileMachineBox");
    const machine3TMB = machine3TMBS.getElementsByClassName("tileMachineBox");
    const machine4TMB = machine4TMBS.getElementsByClassName("tileMachineBox");

    const tabTMB = [machine1TMB, machine2TMB, machine3TMB, machine4TMB];
    const giftList = [gift.machine1, gift.machine2, gift.machine3, gift.machine4];

    if(gift.kodOdpowiedzi = "MACHINE_PLAN"){

        for(let i=0;i<4;i++){
            for(let j=0;j<8;j++){
                var zmienna = giftList[i][j];
                (tabTMB[i])[j].innerHTML = zmienna;

                (tabTMB[i])[j].classList.remove("tileMachineBoxBlack");
                (tabTMB[i])[j].classList.remove("tileMachineBoxGreen");

                if(zmienna == "X"){
                    (tabTMB[i])[j].classList.add("tileMachineBoxBlack");
                }else if(zmienna == ""){

                }else{
                    (tabTMB[i])[j].classList.add("tileMachineBoxGreen");
                }
            }
        }

    }

}

function magazineUpdate(gift){

    for(let i=0; i<bricks.length; i++){
        let value = gift.map[bricks[i].dataset.letters];

        if(value == null){
            value = 0;
        }

        bricks[i].innerHTML = bricks[i].dataset.letters + ": " + value;
    }
}














