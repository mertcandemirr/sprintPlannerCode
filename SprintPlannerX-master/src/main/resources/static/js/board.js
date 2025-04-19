var draggedTask = null;

function allowDrop(event) {
    event.preventDefault();
    var targetList = event.target.tagName === "UL" ? event.target : event.target.parentElement;
    targetList.classList.add("drag-over");
}

function drag(event) {
    draggedTask = event.target;
    event.dataTransfer.setData("number", event.target.dataset.taskId);
    event.target.classList.add("dragging");
}

function drop(event, targetListId) {
    event.preventDefault();
    var taskId = event.dataTransfer.getData("number");
    var targetList = document.getElementById(targetListId);

    if (event.target.tagName === "LI" && event.target.parentElement.id === targetListId) {
        resetDraggingStyles();
        return;
    }

    if (isDuplicateTask(targetList, taskId)) {
        resetDraggingStyles();
        return;
    }

    var taskElement = document.createElement("li");
    taskElement.className = "task";
    taskElement.draggable = true;
    taskElement.addEventListener("dragstart", drag);
    taskElement.appendChild(document.createTextNode(draggedTask.innerText));
    taskElement.dataset.taskId = taskId;

    targetList.classList.remove("drag-over");
    targetList.appendChild(taskElement);

    // Silme işlemi
    if (targetListId === "done" || targetListId === "todo" || targetListId === "overdue") {
        var otherListIds = ["todo", "done", "overdue"].filter(id => id !== targetListId);

        otherListIds.forEach(otherListId => {
            var otherList = document.getElementById(otherListId);
            var otherTasks = otherList.getElementsByClassName("task");
            for (var i = 0; i < otherTasks.length; i++) {
                if (otherTasks[i].dataset.taskId === taskId) {
                    otherList.removeChild(otherTasks[i]);
                    break;
                }
            }
        });


    }
    updateTaskStatus(taskId, targetListId);

    resetDraggingStyles();
}

function updateTaskStatus(taskId, newStatus) {
    fetch('tasks/updateTaskStatus/' + taskId, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: newStatus
    })
        .then(response => response.json())
        .then(data => {
            console.log('Success:', data);
        })
        .catch((error) => {
            console.error('Error:', error);
        });
}

document.addEventListener("dragleave", function (event) {
    var targetList = event.target.tagName === "UL" ? event.target : event.target.parentElement;
    targetList.classList.remove("drag-over");
});

function resetDraggingStyles() {
    draggedTask.classList.remove("dragging");
    draggedTask = null;
}

function isDuplicateTask(list, taskId) {
    var tasks = list.getElementsByClassName("task");
    for (var i = 0; i < tasks.length; i++) {
        if (tasks[i].dataset.taskId === taskId) {
            return true;
        }
    }
    return false;
}
