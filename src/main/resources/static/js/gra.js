var resourcesPane = document.getElementById("resourcesID");
var machinePane = document.getElementById("machineID");

var tileMachineBoxs = document.getElementsByClassName("tileMachineBox");
var parentOftileMachineBoxs = document.getElementsByClassName("tilesMachineBox");
var bricks = document.getElementsByClassName("brick");

window.addEventListener("load", initElements);

function clickResourcesButton(){
	resourcesPane.classList.toggle("resourcesClicked");
	if(resourcesPane.classList.contains("resourcesClicked") == true) {
        sendGiveMeMagazineInfo();
	}
	
	for(let x=0; x < tileMachineBoxs.length; x++){
		tileMachineBoxs[x].classList.toggle("tileMachineBoxClicked");
	}
}

function clickResourcesButtonRemove(){
	resourcesPane.classList.remove("resourcesClicked");

	for(let x=0; x < tileMachineBoxs.length; x++){
		tileMachineBoxs[x].classList.remove("tileMachineBoxClicked");
	}
}

function clickResourcesButtonAdd(){
	resourcesPane.classList.add("resourcesClicked");
    sendGiveMeMagazineInfo();

	for(let x=0; x < tileMachineBoxs.length; x++){
		tileMachineBoxs[x].classList.add("tileMachineBoxClicked");
	}
}


function clickMachineButton(){
    machinePane.classList.toggle("resourcesClicked");

    for(let x=0; x < tileMachineBoxs.length; x++){
    	tileMachineBoxs[x].classList.toggle("tileMachineBoxClicked");
    }
}

function initElements(){
    for(let i=0; i < parentOftileMachineBoxs.length; i++){
        let tiles = parentOftileMachineBoxs[i].getElementsByClassName("tileMachineBox");

        for(let j=0; j<tiles.length; j++){
            tiles[j].onclick = function(){
                tileMachineBoxClicked(tiles[j],i, j);
            }
        }
    }
}

let lastNrMachine = 0;
let lastElement = [null, null];
let tileNrList = [null, null];
let count = 0;

function tileMachineBoxClicked(element, MachineNr, tileNr){
    if(count == 0){
        element.classList.add("tileMenuBoxChosen");

        count = 1;

        lastElement[0] = element;
        lastElement[1] = null;

        tileNrList[0] = tileNr;
        tileNrList[1] = null;

        lastNrMachine = MachineNr;
    } else if(count == 1){
        if(lastElement[0] == element){
            element.classList.remove("tileMenuBoxChosen");

            count = 0;

            lastElement[0] = null;
            lastElement[1] = null;

            tileNrList[0] = null;
            tileNrList[1] = null;

            lastNrMachine = MachineNr;
        }
        else if(lastNrMachine != MachineNr){
            lastElement[0].classList.remove("tileMenuBoxChosen");
            element.classList.add("tileMenuBoxChosen");

            count = 1;

            lastElement[0] = element;
            lastElement[1] = null;

            tileNrList[0] = tileNr;
            tileNrList[1] = null;

            lastNrMachine = MachineNr;
        }
        else if(lastNrMachine == MachineNr){
             element.classList.add("tileMenuBoxChosen");

             count = 2;

             lastElement[1] = element;

             tileNrList[1] = tileNr;

             lastNrMachine = MachineNr;
        }
    }
    else if(count == 2){
        if(lastElement[0] == element){
            element.classList.remove("tileMenuBoxChosen");

            count = 1;

            lastElement[0] = lastElement[1];
            lastElement[1] = null;

            tileNrList[0] = tileNrList[1];
            tileNrList[1] = null;

            lastNrMachine = MachineNr;
        }
        else if(lastElement[1] == element){
             element.classList.remove("tileMenuBoxChosen");

             count = 1;

             lastElement[1] = null;

             tileNrList[1] = null;

             lastNrMachine = MachineNr;
        }
        else if(lastNrMachine != MachineNr){
            lastElement[0].classList.remove("tileMenuBoxChosen");
            lastElement[1].classList.remove("tileMenuBoxChosen");
            element.classList.add("tileMenuBoxChosen");

            count = 1;

            lastElement[0] = element;
            lastElement[1] = null;

            tileNrList[0] = tileNr;
            tileNrList[1] = null;

            lastNrMachine = MachineNr;
        }
        else if(lastNrMachine == MachineNr){
            var bigger = Math.max(tileNrList[0], tileNrList[1]);
            var smaller = Math.min(tileNrList[0], tileNrList[1]);

            var biggerTileNr = null;
            var smallerTileNr = null;

            if(bigger == tileNrList[0]){
                biggerTileNr = 0;
                smallerTileNr = 1;
            }else{
                biggerTileNr = 1;
                smallerTileNr = 0;
            }

            if(smaller > tileNr){
                 element.classList.add("tileMenuBoxChosen");
                 lastElement[smallerTileNr].classList.remove("tileMenuBoxChosen");

                 count = 2;

                 lastElement[smallerTileNr] = element;

                 tileNrList[smallerTileNr] = tileNr;

                 lastNrMachine = MachineNr;
            }
            else if(bigger < tileNr || bigger > tileNr){
                element.classList.add("tileMenuBoxChosen");
                lastElement[biggerTileNr].classList.remove("tileMenuBoxChosen");

                count = 2;

                lastElement[biggerTileNr] = element;

                tileNrList[biggerTileNr] = tileNr;

                lastNrMachine = MachineNr;
            }
        }
    }

    if(count == 2){
        var bigger = Math.max(tileNrList[0], tileNrList[1]);
        var smaller = Math.min(tileNrList[0], tileNrList[1]);

        clickResourcesButtonAdd();

        for(let i=0; i<bricks.length; i++){
            bricks[i].classList.add("brickClickable");

            bricks[i].onclick = function(){
                clickResourcesButtonRemove();

                if(lastElement[0] != null){
                    lastElement[0].classList.remove("tileMenuBoxChosen");
                }

                if(lastElement[1] != null){
                    lastElement[1].classList.remove("tileMenuBoxChosen");
                }

                for(let i=0; i<bricks.length; i++){
                    bricks[i].classList.remove("brickClickable");
                }


                count = 0;

                lastElement[0] = null;
                lastElement[1] = null;

                tileNrList[0] = null;
                tileNrList[1] = null;

                lastNrMachine = MachineNr;

                sendChangeMachinePlan(MachineNr, smaller, bigger, bricks[i].dataset.letters);
            };
        }
    }
    else{
         clickResourcesButtonRemove();
         for(let i=0; i<bricks.length; i++){
            bricks[i].classList.remove("brickClickable");
            bricks[i].onclick = function(){

            };
         }
    }
}