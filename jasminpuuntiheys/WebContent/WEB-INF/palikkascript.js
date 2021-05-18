		async function poistaPalikka(id) {
			let response = await fetch(`/jasminpuuntiheys/poistapalikka?id=${id}`, { method: "DELETE" });
			if (response.status === 200) {
				let element = document.getElementById(`rivi-${id}`);
				element.remove();
			} else {
				alert("Jokin meni pieleen!");
			}
		}