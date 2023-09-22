var resourcesButton = document.getElementById("resourcesID");
var tileMachineBoxs = document.getElementsByClassName("tileMachineBox");

function clickResourcesButton(){
	resourcesButton.classList.toggle("resourcesClicked");
	
	for(let x=0; x < tileMachineBoxs.length; x++){
		tileMachineBoxs[x].classList.toggle("tileMachineBoxClicked");
	}
}