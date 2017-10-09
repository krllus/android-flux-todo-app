package lgvalle.com.fluxtodo;

import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.style.StrikethroughSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import lgvalle.com.fluxtodo.actions.ActionsCreator;
import lgvalle.com.fluxtodo.model.Todo;


class TodoRecyclerAdapter extends RecyclerView.Adapter<TodoRecyclerAdapter.ViewHolder> {

    private static ActionsCreator actionsCreator;
    private List<Todo> todos;

    TodoRecyclerAdapter(ActionsCreator actionsCreator) {
        this.todos = new ArrayList<>();
        TodoRecyclerAdapter.actionsCreator = actionsCreator;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.todo_row_layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.bindView(todos.get(i));
    }

    @Override
    public int getItemCount() {
        return todos.size();
    }

    void setItems(List<Todo> todos) {
        this.todos = todos;
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView todoText;
        CheckBox todoCheck;
        Button todoDelete;

        ViewHolder(View v) {
            super(v);
            todoText = v.findViewById(R.id.row_text);
            todoCheck = v.findViewById(R.id.row_checkbox);
            todoDelete = v.findViewById(R.id.row_delete);

        }

        void bindView(final Todo todo) {
            if (todo.isComplete()) {
                SpannableString spanString = new SpannableString(todo.getText());
                spanString.setSpan(new StrikethroughSpan(), 0, spanString.length(), 0);
                todoText.setText(spanString);
            } else {
                todoText.setText(todo.getText());
            }


            todoCheck.setChecked(todo.isComplete());
            todoCheck.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    actionsCreator.toggleComplete(todo);
                }
            });

            todoDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    actionsCreator.destroy(todo.getId());
                }
            });
        }
    }
}
