document.addEventListener("DOMContentLoaded", () => {
    const addBtn = document.getElementById("addTaskBtn");
    const cancelBtn = document.getElementById("cancelAddTaskBtn");

    if (addBtn) {
        addBtn.addEventListener("click", toggleAddTask);
    }

    if (cancelBtn) {
        cancelBtn.addEventListener("click", toggleAddTask);
    }
});

function toggleAddTask() {
    const form = document.getElementById("addTaskForm");
    if (form) {
        form.classList.toggle("hidden");
    }
}
