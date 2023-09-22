var client = null;

const machine1TMBS = document.getElementById("maszyna1TMB");
const machine2TMBS = document.getElementById("maszyna2TMB");
const machine3TMBS = document.getElementById("maszyna3TMB");
const machine4TMBS = document.getElementById("maszyna4TMB");

const saldoDiv = document.getElementById("saldoInfo");
const dayDiv = document.getElementById("dayInfo");

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

function sendDay(day){
    client.send("/app/drzwi", {}, JSON.stringify({giveOrTake: "take", order: "MACHINE_PLAN", addInfo: day}));
}

function sendInit(){
    client.send("/app/drzwi", {}, JSON.stringify({giveOrTake: "take", order: "INIT"}));
}

function doIt(gift){

    if(gift.kodOdpowiedzi == "MACHINE_PLAN"){
        machinePlanMethod(gift);
    }
    else if(gift.kodOdpowiedzi == "INIT"){
        initMethod(gift);
    }

}

function initMethod(gift){
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