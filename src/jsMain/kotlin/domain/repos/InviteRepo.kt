package domain.repos

import kotlinx.coroutines.flow.Flow
import model.InvitationsState

interface InviteRepo {

    fun inviteUser(username: String)
    fun acceptInvite(username: String)
    fun observeNotifications(): Flow<InvitationsState>
}