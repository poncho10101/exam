package com.alfonsosotelo.examtestingapp.ui.users

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.alfonsosotelo.examtestingapp.R
import com.alfonsosotelo.examtestingapp.repository.remote.objects.User
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.user_row.view.*

class UserAdapter: ListAdapter<User, UserAdapter.UserViewHolder>(DefaultDiffCallback()) {


    protected val clickSubject = PublishSubject.create<ClickListener>()

    fun getClickObservable(): Observable<ClickListener> = clickSubject


    inner class UserViewHolder(view: View): RecyclerView.ViewHolder(view) {
        fun bind(element: User) {
            itemView.apply {
                tvEmail.text = element.email
                tvTitle.text = element.username

                setOnClickListener {
                    clickSubject.onNext(ClickListener(element, this@UserViewHolder))
                }
            }
        }

    }


    /**
     * This class is a Default Diff Item Callback for RealmObjects implementing BaseEntity
     */
    class DefaultDiffCallback: DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.email == newItem.email
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.user_row, parent, false))
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ClickListener(val entity: User, val viewHolder: UserViewHolder)
}